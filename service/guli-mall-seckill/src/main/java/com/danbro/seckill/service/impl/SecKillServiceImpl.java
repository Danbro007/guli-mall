package com.danbro.seckill.service.impl;

import com.danbro.common.utils.*;
import com.danbro.seckill.service.PmsFeignService;
import com.danbro.seckill.service.SecKillService;
import com.danbro.seckill.service.SmsFeignService;
import com.danbro.seckill.vo.PmsSkuInfoVo;
import com.danbro.seckill.vo.SmsSeckillSessionVo;
import com.danbro.seckill.vo.SmsSeckillSkuRelationVo;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Danrbo
 * @Classname SecKillServiceImpl
 * @Description TODO
 * @Date 2021/3/16 12:24
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    public static final String SEC_KILL_SESSIONS_PREFIX = "secKill:sessions";
    public static final String SEC_KILL_SKUS_PREFIX = "secKill:skus";
    public static final String SEC_KILL_STOCK_PREFIX = "secKill:stock";
    @Autowired
    SmsFeignService smsFeignService;

    @Autowired
    PmsFeignService pmsFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;


    @Override
    public void uploadLast3DaysSku() {
        // 查询到最近三天要上线的活动
        List<SmsSeckillSessionVo> smsSecKillSessionList = MyCurdUtils.rpcResultHandle(smsFeignService.getLast3DaysSku(), false);
        if (MyCollectionUtils.isNotEmpty(smsSecKillSessionList)) {
            saveSessionInfoList(smsSecKillSessionList);
        }
    }

    @Override
    public List<SmsSeckillSkuRelationVo> getCurrentSecKillSkuList() {
        long currentTime = System.currentTimeMillis();
        // 先获取所有秒杀活动
        Set<String> promotions = redisTemplate.keys(SEC_KILL_SESSIONS_PREFIX + "*");
        if (promotions != null && !promotions.isEmpty()) {
            // 过滤出已经开始但还没结束的秒杀活动
            // 例如：secKill:sessions1616115660000-1616158800000
            ArrayList<SmsSeckillSkuRelationVo> skuRelationVos = new ArrayList<>();
            promotions.stream().filter(promotion -> {
                String timeString = promotion.replace(SEC_KILL_SESSIONS_PREFIX, "");
                String[] timeArray = timeString.split("-");
                Long startTime = Long.parseLong(timeArray[0]);
                Long endTime = Long.parseLong(timeArray[1]);
                // 当前活动处于秒杀中
                return currentTime >= startTime && currentTime <= endTime;
            }).forEach(promotion -> {
                // 获取当前秒杀活动里的商品SkuId
                List<String> skuIdList = redisTemplate.opsForList().range(promotion, -100, 100);
                if (skuIdList != null && !skuIdList.isEmpty()) {
                    BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(SEC_KILL_SKUS_PREFIX);
                    // 通过SkuId查询出商品的Json字符串
                    List<String> skuJsonList = boundHashOps.multiGet(skuIdList);
                    if (skuJsonList != null && !skuJsonList.isEmpty()) {
                        List<SmsSeckillSkuRelationVo> collect = skuJsonList.stream().map(skuJson -> MyJSONUtils.parseObject(skuJson, SmsSeckillSkuRelationVo.class)).collect(Collectors.toList());
                        skuRelationVos.addAll(collect);
                    }
                }
            });
            return skuRelationVos;
        }
        return null;
    }

    @Override
    public SmsSeckillSkuRelationVo getSecKillRelationInfo(Long skuId) {
        BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(SEC_KILL_SKUS_PREFIX);
        Set<String> skuIdList = boundHashOps.keys();
        if (skuIdList != null && !skuIdList.isEmpty()) {
            // 正则匹配
            for (String id : skuIdList) {
                String s = id.split("_")[1];
                if (s.equals(skuId.toString())) {
                    String json = boundHashOps.get(id);
                    SmsSeckillSkuRelationVo relationVo = MyJSONUtils.parseObject(json, SmsSeckillSkuRelationVo.class);
                    // 判断秒杀的商品在不在时间范围内在的话则把随机码一起返回
                    if (relationVo != null) {
                        long currentTime = System.currentTimeMillis();
                        Long startTime = relationVo.getStartTime();
                        Long endTime = relationVo.getEndTime();
                        if (currentTime > endTime || currentTime < startTime) {
                            relationVo.setRandomCode(null);
                        }
                        return relationVo;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String killSku(String killId, String randomCode, Integer num) {
        // 获取秒杀的商品信息
        BoundHashOperations<String, String, String> operations = redisTemplate.boundHashOps(SEC_KILL_SKUS_PREFIX);
        String json = operations.get(killId);
        if (MyStrUtils.isNotEmpty(json)) {
            // 秒杀产品的信息
            SmsSeckillSkuRelationVo relationVo = MyJSONUtils.parseObject(json, SmsSeckillSkuRelationVo.class);
            if (relationVo != null) {
                // 判断秒杀的场次、商品是否正确以及秒杀是否超时
                if (isCurrentSecKillPromotion(relationVo, killId) &&
                        isSecKillOverTime(relationVo) &&
                        checkRandomCode(relationVo.getRandomCode(), randomCode)) {
                    // 校验每个人秒杀的数量
                    if (relationVo.getSeckillCount().compareTo(new BigDecimal(num.toString())) == 0) {
                        // 获取信号量
                        RSemaphore semaphore = redissonClient.getSemaphore(SEC_KILL_STOCK_PREFIX + randomCode);
                        try {
                            // 获取到信号量
                            boolean acquire = semaphore.tryAcquire(num, 100, TimeUnit.MILLISECONDS);
                            if (acquire) {
                                // 防止单个用户重复秒杀
                                // key:userId_sessionId_skuId
                                String key =
                                        redisTemplate.opsForValue().setIfAbsent()
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    /**
     * 判断秒杀是不是超时
     *
     * @param relationVo
     */
    private Boolean isSecKillOverTime(SmsSeckillSkuRelationVo relationVo) {
        // 判断是不是在秒杀时间内
        long currentTime = System.currentTimeMillis();
        Long startTime = relationVo.getStartTime();
        Long endTime = relationVo.getEndTime();
        return currentTime >= startTime && currentTime <= endTime;
    }

    /**
     * 是不是当前的秒杀活动：活动场次ID_SkuId
     *
     * @param relationVo
     * @return
     */
    private Boolean isCurrentSecKillPromotion(SmsSeckillSkuRelationVo relationVo, String killId) {
        return (relationVo.getPromotionId() + "_" + relationVo.getSkuId()).equals(killId);
    }

    /**
     * 校验秒杀商品的随机码
     *
     * @param originRandomCode
     * @param toCheckRandomCode
     * @return
     */
    private Boolean checkRandomCode(String originRandomCode, String toCheckRandomCode) {
        if (MyStrUtils.isNotEmpty(toCheckRandomCode)) {
            return originRandomCode.equals(toCheckRandomCode);
        }
        return false;
    }

    /**
     * 例如：
     * key:secKill:sessions:startTime-endTime
     * value:[1,2,3,4,5]
     *
     * @param smsSecKillSessionList
     */
    private void saveSessionInfoList(List<SmsSeckillSessionVo> smsSecKillSessionList) {
        smsSecKillSessionList.stream().filter(session -> MyCollectionUtils.isNotEmpty(session.getRelationList())).forEach(session -> {
            long startTime = session.getStartTime().getTime();
            long endTime = session.getEndTime().getTime();
            String key = SEC_KILL_SESSIONS_PREFIX + startTime + "-" + endTime;
            List<String> skuIdList = session.getRelationList().stream().map(relation -> session.getId() + "_" + relation.getSkuId().toString()).collect(Collectors.toList());
            // 如果key存在则不把秒杀活动信息再次放入缓存中
            if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
                redisTemplate.opsForList().leftPushAll(key, skuIdList);
                List<SmsSeckillSkuRelationVo> relationList = session.getRelationList();
                BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(SEC_KILL_SKUS_PREFIX);
                relationList.forEach(relation -> {
                    PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsFeignService.getSkuInfo(relation.getSkuId()), false);
                    // 如果秒杀产品存在则不放入缓存中
                    if (pmsSkuInfoVo != null) {
                        String secKillSkuKey = relation.getPromotionSessionId() + "_" + pmsSkuInfoVo.getSkuId();
                        if (Boolean.FALSE.equals(operations.hasKey(secKillSkuKey))) {
                            relation.setStartTime(startTime).setEndTime(endTime);
                            relation.setSkuInfo(pmsSkuInfoVo);
                            relation.setRandomCode(MyRandomUtils.randomUUID().replace("-", ""));
                            // 分布式信号量
                            if (Boolean.FALSE.equals(redisTemplate.hasKey(SEC_KILL_STOCK_PREFIX + relation.getRandomCode()))) {
                                RSemaphore semaphore = redissonClient.getSemaphore(SEC_KILL_STOCK_PREFIX + relation.getRandomCode());
                                semaphore.trySetPermits(Integer.parseInt(relation.getSeckillCount().toString()));

                            }
                            operations.put(secKillSkuKey, MyJSONUtils.toJSONString(relation));
                        }
                    }

                });
            }

        });
    }
}
