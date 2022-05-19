package com.lsh.class06.juc;

import java.util.concurrent.Semaphore;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 3:25 下午
 * @desc ：Semaphore 信号灯
 *
 * Semaphore semaphore = new Semaphore(2,true);  第二个参数为true 表示为公平锁
 */
public class Code04_Semaphore {
    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(1); //允许一个线程同时执行
        Semaphore semaphore = new Semaphore(2); //允许两个线程同时执行
//        Semaphore semaphore = new Semaphore(2,true);
        new Thread(()->{
            try {
                //获得名额
                semaphore.acquire();
                System.out.println("t1 running...");
                Thread.sleep(2000);
                System.out.println("t1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //还回名额
                semaphore.release();
            }

        }).start();
        new Thread(()->{
            try {
                //获得名额
                semaphore.acquire();
                System.out.println("t2 running...");
                Thread.sleep(2000);
                System.out.println("t2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //还回名额
                semaphore.release();
            }

        }).start();
    }

}
