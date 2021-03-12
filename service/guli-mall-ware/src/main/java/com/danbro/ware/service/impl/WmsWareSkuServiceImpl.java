package com.danbro.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.oms.OrderItemLockStatus;
import com.danbro.common.enums.oms.OrderStatus;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.*;
import com.danbro.ware.config.MyRabbitMqConfig;
import com.danbro.ware.controller.vo.*;
import com.danbro.ware.entity.WmsWareOrderTask;
import com.danbro.ware.entity.WmsWareOrderTaskDetail;
import com.danbro.ware.entity.WmsWareSku;
import com.danbro.ware.mapper.WmsWareSkuMapper;
import com.danbro.ware.service.WmsWareOrderTaskDetailService;
import com.danbro.ware.service.WmsWareOrderTaskService;
import com.danbro.ware.service.WmsWareSkuService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 商品库存(WmsWareSku)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku> implements WmsWareSkuService {
    @Autowired
    WmsWareOrderTaskDetailService wmsWareOrderTaskDetailService;

    @Autowired
    WmsWareOrderTaskService wmsWareOrderTaskService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    OmsFeignService omsFeignService;

    @Override
    public Pagination<WmsWareSkuVo, WmsWareSku> queryPageByCondition(PageParam<WmsWareSku> pageParam, Long skuId, Long wareId) {
        LambdaQueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<WmsWareSku>().lambda();
        if (MyObjectUtils.isNotNull(skuId) && skuId > 0) {
            queryWrapper.like(WmsWareSku::getSkuId, skuId).or().eq(WmsWareSku::getSkuId, skuId);
        }
        if (MyObjectUtils.isNotNull(wareId) && wareId > 0) {
            queryWrapper.eq(WmsWareSku::getWareId, wareId);
        }
        return new Pagination<>(this.page(new Query<WmsWareSku>().getPage(pageParam), queryWrapper), WmsWareSkuVo.class);
    }

    @Override
    public WmsWareSkuVo insertWareSku(WmsWareSkuVo wmsWareSkuVo) {
        WmsWareSku wmsWareSku = wmsWareSkuVo.convertToEntity();
        boolean result = this.save(wmsWareSku);
        return MyCurdUtils.insertOrUpdate(wmsWareSkuVo.convertToVo(wmsWareSku), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsWareSkuVo updateWareSku(WmsWareSkuVo wmsWareSkuVo) {
        WmsWareSku wmsWareSku = wmsWareSkuVo.convertToEntity();
        boolean result = this.updateById(wmsWareSku);
        return MyCurdUtils.insertOrUpdate(wmsWareSkuVo.convertToVo(wmsWareSku), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public WmsWareSkuVo getWareSkuInfoById(Long wareSkuId) {
        WmsWareSku wmsWareSku = this.getById(wareSkuId);
        return MyCurdUtils.select(WmsWareSkuVo.builder().build().convertToVo(wmsWareSku), ResponseCode.NOT_FOUND);
    }

    @Override
    public void batchDeleteWareSku(List<Long> wareSkuIdList) {
        MyCurdUtils.batchDelete(this.removeByIds(wareSkuIdList), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public Boolean hasStockBySkuId(Long skuId) {
        List<WmsWareSku> wareSkuList = this.list(new QueryWrapper<WmsWareSku>().lambda().eq(WmsWareSku::getSkuId, skuId));
        // 没查找库存数据就返回false
        if (MyCollectionUtils.isEmpty(wareSkuList)) {
            return false;
        }
        // 计算出 sku 的总库存数（所有仓库的库存）
        AtomicReference<Integer> totalStock = new AtomicReference<>(0);
        wareSkuList.forEach(sku -> totalStock.updateAndGet(v -> v + sku.getStock()));
        return totalStock.get() > 0;
    }

    /**
     * 这里有两种情况要考虑：
     * 1、本地方法抛出异常，会导致 WmsWareOrderTask、WmsWareSku、WmsWareOrderTaskDetail 这三个表数据回滚。
     * 这种情况不用发送消息到消息队列
     * 2、本地方法执行成功，但是接下来其他服务会执行出错，则通过延迟消息队列进行库存释放。
     *
     * @param responseVo 要锁库存的商品列表
     * @return 锁库存的结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<WmsLockStockResultVo> lockStock(OrderToResponseVo responseVo) {
        List<WmsLockStockResultVo> resultVos = new ArrayList<>();
        // 1、循环遍历所有的商品
        for (OmsOrderItemVo item : responseVo.getItems()) {
            // 锁库存成功标志符号，默认是 false
            boolean lockItemSuccess = false;
            // 2、查找出有当前商品库存的仓库ID（库存数-被锁定的库存数）
            List<Long> wareIdList = this.baseMapper.getWareIdListHasStock(item.getSkuId());
            // 4、遍历所有的仓库开始锁库存
            for (Long wareId : wareIdList) {
                Long result = this.baseMapper.lockStockBySkuId(item.getSkuId(), wareId, item.getSkuQuantity());
                // 锁库存成功跳出循环锁下一个商品的库存
                if (result == 1) {
                    WmsLockStockResultVo resultVo = new WmsLockStockResultVo().
                            setStock(item.getSkuQuantity()).
                            setSkuId(item.getSkuId()).
                            setWareId(wareId).
                            setSuccess(true);
                    resultVos.add(resultVo);
                    lockItemSuccess = true;
                    break;
                }
            }
            // 只要有一个锁库存失败则停止后面的锁库存操作，进行库存回滚
            if (!lockItemSuccess) {
                throw new GuliMallException(ResponseCode.LOCK_STOCK_FAILURE);
            }
        }
        // 到这里说明库存都锁成功了，
        // 创建 orderTask
        WmsWareOrderTask wareOrderTask = createWareOrderTask(responseVo.getOrder());
        boolean saveOrderTaskResult = wmsWareOrderTaskService.save(wareOrderTask);
        MyCurdUtils.insertOrUpdate(saveOrderTaskResult, ResponseCode.INSERT_FAILURE);
        // 保存 WmsWareOrderTaskDetail
        List<WmsWareOrderTaskDetail> taskDetails = resultVos.stream().map(result -> WmsWareOrderTaskDetail.builder().
                skuId(result.getSkuId()).
                wareId(result.getWareId()).
                lockStatus(OrderItemLockStatus.LOCKED).
                skuNum(result.getStock()).
                taskId(wareOrderTask.getId()).build()).collect(Collectors.toList());
        MyCurdUtils.batchInsertOrUpdate(taskDetails, wmsWareOrderTaskDetailService.saveBatch(taskDetails), ResponseCode.INSERT_FAILURE);
        // 发送消息到延迟队列
        rabbitTemplate.convertAndSend(MyRabbitMqConfig.STOCK_EVENT_EXCHANGE, MyRabbitMqConfig.STOCK_LOCKED_ROUTING_KEY, taskDetails);
        return resultVos;
    }

    @Override
    public Boolean releaseLockStock(Integer stock, Long skuId, Long wareId) {
        return this.baseMapper.releaseStockLock(stock, skuId, wareId) == 1;
    }

    /**
     * 构建库存订单任务
     *
     * @param omsOrderVo 订单对象
     * @return 库存订单任务
     */
    private WmsWareOrderTask createWareOrderTask(OmsOrderVo omsOrderVo) {
        return WmsWareOrderTask.builder().orderSn(omsOrderVo.getOrderSn()).deliveryAddress(omsOrderVo.getReceiverDetailAddress()).
                consignee(omsOrderVo.getReceiverName()).consigneeTel(omsOrderVo.getReceiverPhone()).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releaseStock(List<WmsWareOrderTaskDetail> detailList) {
        // 1、查询订单的状态，如果还是 WAIT_PAY、CLOSED、INVALID_ORDER 状态则解锁库存
        Long taskId = detailList.get(0).getTaskId();
        // wareOrderTask 没找到则说明库存记录也没有
        WmsWareOrderTask wareOrderTask = wmsWareOrderTaskService.getOne(new QueryWrapper<WmsWareOrderTask>().lambda().eq(WmsWareOrderTask::getId, taskId));
        if (MyObjectUtils.isNotNull(wareOrderTask)) {
            // 情况1：订单存在但是过了规定时间没有付款
            // 情况2：订单不存在则可能在创建订单时候订单记录回滚了，但是库存记录没有回滚导致库存被锁定了
            OmsOrderVo omsOrderVo = MyCurdUtils.rpcResultHandle(omsFeignService.getOrderInfoByOrderSn(wareOrderTask.getOrderSn()), false);
            if (omsOrderVo == null || omsOrderVo.getStatus() < OrderStatus.DELIVERED) {
                // 2、解锁库存
                doReleaseStock(detailList);
            }
        }
    }

    private void doReleaseStock(List<WmsWareOrderTaskDetail> detailList) {
        for (WmsWareOrderTaskDetail wareOrderTaskDetail : detailList) {
            // 2.1 到 wms_ware_order_task_detail 里查看还处于库存锁定状态的记录
            // 如果查不到就不进行库存释放
            WmsWareOrderTaskDetail detail = wmsWareOrderTaskDetailService.
                    getOne(new QueryWrapper<WmsWareOrderTaskDetail>().lambda().
                            eq(WmsWareOrderTaskDetail::getId, wareOrderTaskDetail.getId()).
                            eq(WmsWareOrderTaskDetail::getLockStatus, OrderItemLockStatus.LOCKED));
            if (MyObjectUtils.isNotNull(detail)) {
                // 解锁库存
                MyCurdUtils.insertOrUpdate(this.releaseLockStock(detail.getSkuNum(), detail.getSkuId(), detail.getWareId()), ResponseCode.UPDATE_FAILURE);
                // 锁定状态修改为解锁状态
                detail.setLockStatus(OrderItemLockStatus.UNLOCKED);
                MyCurdUtils.insertOrUpdate(wmsWareOrderTaskDetailService.updateById(detail), ResponseCode.UPDATE_FAILURE);
            }
        }
    }
}