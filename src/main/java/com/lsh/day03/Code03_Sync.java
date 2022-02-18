package com.lsh.day03;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 3:36 下午
 * @desc ：并发编程三大特性-原子性
 * 多个进程去访问共享数据的时候回产生竞争 -> race condition
 */
public class Code03_Sync {

    private static long num = 0L;

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    num++;
                }
                latch.countDown();
            });

        }
        for (Thread thread:threads){
            thread.start();
        }
        //正常来说num应该是1000000
        System.out.println(num);
    }
}
