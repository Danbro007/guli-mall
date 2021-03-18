package com.danbro.kill.scheduledTask;

import com.danbro.kill.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Danrbo
 * @Classname ScheduleTask
 * @Description TODO
 * @Date 2021/3/16 11:07
 */
@Component
@Slf4j
public class ScheduleTask {
    public static final String SEC_KILL_UPLOAD_LOCK = "secKill:upload:lock";
    @Autowired
    SecKillService secKillService;

    @Autowired
    RedissonClient redissonClient;

    @Async
    @Scheduled(cron = "* * * * * ?")
    public void task() {
        RLock lock = redissonClient.getLock(SEC_KILL_UPLOAD_LOCK);
        try {
            lock.lock();
            secKillService.uploadLast3DaysSku();
        } finally {
            lock.unlock();
        }
    }
}
