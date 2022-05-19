package com.lsh.class06.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 11:06 上午
 * @desc ：CyclicBarrier 循环屏障：等到线程堆满了，线程在继续向下执行
 * cyclicBarrier相当一个栅栏，一个线程来了卡在这里，等到达到 parties 线程数了，允许这批线程继续向下执行
 */
public class Code02_CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(120, () -> {
            System.out.println("============》满人，发车！");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    System.out.println("来了一个人");
                    cyclicBarrier.await();
                    System.out.println("吃饭");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
