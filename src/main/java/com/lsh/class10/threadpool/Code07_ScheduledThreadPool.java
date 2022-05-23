package com.lsh.class10.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 12:15 上午
 * @desc ：ScheduledThreadPool  定时任务线程池  将任务延时多长时间执行
 *
 * ScheduledThreadPoolExecutor
 * 最大线程数 还是 Integer.MAX
 * 使用的是DelayedWorkQueue 延时阻塞队列
 *
 * 定时任务框架： quartz spring定时任务
 *
 *
 *
 */
public class Code07_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        System.out.println("任务开始时间："+new Date());
        service.schedule(()->{
            System.out.println("任务执行时间："+new Date());
            /**
             * Tue May 24 00:27:13 CST 2022
             * Tue May 24 00:27:43 CST 2022
             */
        },30, TimeUnit.SECONDS);
        service.shutdown();
    }

}
