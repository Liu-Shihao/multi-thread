package com.lsh.createthread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 1:39 下午
 * @desc ：通过ThreadPoolExecutor的构造方法创建线程池
 */
public class CreateThreadByThreadPoolExecutor {
    public static void main(String[] args) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPollSize = 50;
        int queueSize = 100;
        ExecutorService executor = new ThreadPoolExecutor(corePoolSize,
                maxPollSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));

    }
}
