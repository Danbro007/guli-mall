package com.danbro.seckill.scheduledTask;

/**
 * @author Danrbo
 * @Classname ScheduleTask
 * @Description TODO
 * @Date 2021/3/16 11:07
 */
//@Component
//@Slf4j
//public class ScheduleTask {
//    public static final String SEC_KILL_UPLOAD_LOCK = "secKill:upload:lock";
//    @Autowired
//    SecKillService secKillService;
//
//    @Autowired
//    RedissonClient redissonClient;
//
//    @Async
//    @Scheduled(cron = "* * * * * ?")
//    public void task() {
//        RLock lock = redissonClient.getLock(SEC_KILL_UPLOAD_LOCK);
//        try {
//            lock.lock();
//            secKillService.uploadLast3DaysSku();
//        } finally {
//            lock.unlock();
//        }
//    }
//}
