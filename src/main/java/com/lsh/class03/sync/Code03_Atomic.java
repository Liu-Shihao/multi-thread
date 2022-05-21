package com.lsh.class03.sync;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 4:27 下午
 * @desc ：原子
 * 使用 cas 解决多线程问题
 *
 */
public class Code03_Atomic {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static void m(){
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    m();
                }
                latch.countDown();
            }).start();
        }
        latch.await();

        System.out.println(atomicInteger.get());

    }
}
