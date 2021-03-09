package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.product.entity.OmsOrderOperateHistory;
import com.danbro.product.mapper.OmsOrderOperateHistoryMapper;
import com.danbro.product.service.OmsOrderOperateHistoryService;
import org.springframework.stereotype.Service;

/**
 * 订单操作历史记录(OmsOrderOperateHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Service
public class OmsOrderOperateHistoryServiceImpl extends ServiceImpl<OmsOrderOperateHistoryMapper, OmsOrderOperateHistory> implements OmsOrderOperateHistoryService {
    
}