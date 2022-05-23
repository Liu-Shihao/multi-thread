package com.lsh.class10.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/23 11:41 下午
 * @desc ：单例线程池 线程池中只有一个线程  可以保证任务是顺序执行的
 * 为什么要有单线程的线程池？
 *  1.线程池是有现成队列的
 *  2.任务队列，生命周期管理
 */
public class Code04_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
                /**
                 * 0 pool-1-thread-1
                 * 1 pool-1-thread-1
                 * 2 pool-1-thread-1
                 * 3 pool-1-thread-1
                 * 4 pool-1-thread-1
                 */
            });
        }
        executorService.shutdown();

    }
}
