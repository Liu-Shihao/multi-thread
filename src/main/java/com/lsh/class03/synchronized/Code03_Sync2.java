package com.lsh.day03_sync_volatile;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 3:59 下午
 * @desc ：上锁的本质就是把并发的编程序列化
 * synchronized保证 原子性、可见性，但是不保证有序性。
 *
 * 1. 悲观的认为这个操作会被别的线程打断（悲观锁）synchronized（上一个小程序）
 *
 * 2. 乐观的认为这个做不会被别的线程打断（乐观锁 自旋锁 无锁）cas操作
 *
 * Synchronized 修饰的锁是一个对象的时候，对象不能改变
 */
public class Code03_Sync2 {
    private static final Object  o = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                /**
                 * synchronized (o 管程){
                 *  临界区 大 锁的粒度大；小 粒度小
                 * }
                 */
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+" start!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" end!");
                }

            }).start();

        }
    }
}
