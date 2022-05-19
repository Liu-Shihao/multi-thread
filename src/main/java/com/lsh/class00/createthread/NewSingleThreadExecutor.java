package com.lsh.class00.createthread;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/24 1:45 下午
 * @desc ：创建一个单线程化的线程池
 * 单线程化线程池(newSingleThreadExecutor)的优点，串行执行所有任务。
 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 * 当shutdown时停止线程
 */
public class NewSingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            singleThreadExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "睡觉前");
                try {
                    System.out.println(Thread.currentThread().getName() + "睡觉中...");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
                System.out.println(Thread.currentThread().getName() + "睡觉后");
            });
        singleThreadExecutor.shutdown();
        System.out.println(Instant.now()+"停止线程");

    }
}
