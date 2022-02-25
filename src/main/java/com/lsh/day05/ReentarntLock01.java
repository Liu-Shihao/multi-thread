package com.lsh.day05;

import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 10:15 上午
 * @desc ：ReentarntLock 可重入锁
 *
 */
public class ReentarntLock01 {
    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5){
                m2();
            }
            System.out.println(i);
        }
    }
    synchronized void m2(){
        System.out.println("m2...");
    }

    /**
     * 1. 此时第二个线程得不到锁，所以无法执行
     * 2. 如果是同一个线程，则m1、m2可以同时执行，如在m1中调用m2
     * @param args
     */
    public static void main(String[] args) {
        ReentarntLock01 reentarntLock01 = new ReentarntLock01();
        new Thread(reentarntLock01::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentarntLock01::m2).start();
    }
}
