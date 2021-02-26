package com.lsh.createthread;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/24 1:45 下午
 * @desc ：创建可定时执行的线程池
 */
public class NewScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        System.out.println(Instant.now());
        scheduledThreadPool.schedule(()->{
            System.out.println("延时2秒执行---");
            System.out.println(Instant.now());
        },2, TimeUnit.SECONDS);
    }
}
