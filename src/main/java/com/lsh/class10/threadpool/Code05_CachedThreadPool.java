package com.lsh.class10.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/23 11:52 下午
 * @desc ：缓存线程池 CachedThreadPool
 * 核心线程数为0   最大线程数为Integer.MAX
 * 注意：任务队列使用的 SynchronousQueue 同步队列
 * 线程池的执行流程：
 * 首先核心线程数执行，然后放入阻塞队列，阻塞队列满了，在开启新线程执行（最大线程数）
 * 但是根据SynchronousQueue 同步队列的特性：任务存放进队列后，只有等待任务被新线程执行
 *
 * 所以CachedThreadPool即来一个任务就创建一个新的线程执行，容易产生OOM 一般不会使用
 *
 */
public class Code05_CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
            
        }
        System.out.println(service);
        try {
            Thread.sleep(80000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(service);
    }
}
