package com.lsh.class01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/15 10:58 下午
 * @desc ：线程状态
 *
 * 只有 synchronized 同步代码块 才会进入 BLOCKED 状态
 * lock.lock();、Thread.sleep(2000); park() 线程进入WAITING或者TIMED_WAITING状态
 *
 */
public class Code05_ThreadState {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            //线程执行中  RUNNABLE状态
            System.out.println("2 RUNNABLE ："+Thread.currentThread().getState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //new 创建出线程，未调用start方法 是NEW状态
        System.out.println("1 NEW ："+t1.getState());
        t1.start();
        Thread.sleep(1000);
        //t1再执行时run方法内部调用sleep方法 ，线程为TIMED_WAITING状态
        System.out.println("3 TIMED_WAITING："+t1.getState());
        //t1.join(); 等待t1线程结束
        t1.join();
        //线程任务完成后为  TERMINATED    状态
        System.out.println("4 TERMINATED："+t1.getState());
        final Object o = new Object();
        Thread t3 = new Thread(() -> {
            synchronized (o){
                System.out.println("o 获得了锁🔐");
            }
        });
        new Thread(()->{
            synchronized (o){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(1000);
        t3.start();
        Thread.sleep(1000);
        // t3 执行时，synchronized 等待获得 o的锁，此时锁被占用了，t3线程就处于 BLOCKED 状态
        System.out.println("5 BLOCKED："+t3.getState());
        //===================
        //JUC（java.util.concurrent.locks） 的锁  一般通过CAS实现
        final Lock lock = new ReentrantLock();
        Thread t4 = new Thread(() -> {
            lock.lock();
            System.out.println("t4 得到了锁");
            lock.unlock();
        });
        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
        Thread.sleep(1000);
        t4.start();
        Thread.sleep(1000);

        System.out.println("6 WAITING："+t4.getState());


    }
}
