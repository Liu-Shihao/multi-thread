package com.lsh.createthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/22 5:34 下午
 * @desc ：创一个固定线程池
 * 线程和数据库连接这些资源都是非常宝贵的资源。那么每次需要的时候创建，不需要的时候销毁，是非常浪费资源的。那么我们就可以使用缓存的策略，也就是使用线程池。
 * 1.newCachedThreadPool  缓存线程池
 * 2.newFixedThreadPool   固定线程池
 * 3.newScheduledThreadPool  预定线程池
 * 4.newSingleThreadExecutor  单线程执行器
 *
 */
public class NewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        while(true){
            threadPool.execute(() ->{
                System.out.println(Thread.currentThread().getName()+" is running...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}