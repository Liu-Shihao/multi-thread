package com.lsh.class06.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 10:58 上午
 * @desc ：CountDownLatch  倒计时
 *
 * 等待线程可以用 CountDownLatch、join
 */
public class Code01_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    latch.countDown();//-1
//                    System.out.println(latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        System.out.println(latch.getCount());
        latch.await();//等待
        System.out.println(latch.getCount());
    }
}
