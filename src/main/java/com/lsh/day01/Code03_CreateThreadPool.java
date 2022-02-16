package com.lsh.day01;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/14 4:20 下午
 * @desc ：
 */
public class Code03_CreateThreadPool {
    public static void main(String[] args) throws Exception {

        /**
         * 构造方法
         * 面试重点：
         * 使用默认的线程工厂创建的线程是t.setDaemon(false);
         * 使用默认的拒绝策略是直接抛出RejectedExecutionException异常
         *
         */
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
//                60L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(5));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());


        int cps = 1;
        int mps = 2;
        int c = 5;
        int size = mps + c;
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.execute(() ->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("----------------");
        //此时线程池达到线程最大值，队列已满，  再加一个线程则会直接抛出RejectedExecutionException异常
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.execute(() ->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
