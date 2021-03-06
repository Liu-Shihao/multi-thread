package com.lsh.class06.juc;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 4:07 下午
 * @desc ：淘宝 面试题：
 * 1.线程可见问题
 *
 * object.wait() 线程等待，调用后会释放锁对象
 * object.notify() 唤醒线程
 *
 * 1. 线程2开始执行，当size ！= 5 时 则调用wait()方法进入等待状态
 * 2. 线程1开始执行，当size增加到5时，将线程2唤醒，线程1进入等待
 * 3. 线程2运行完结束，唤醒线程1
 * 4. 线程1 执行完成 结束
 *
 */
public class Test01 {
    volatile LinkedList arrayList =  new LinkedList<Integer>();

    public void add(int v){
        arrayList.add(v);
    }

    public int size(){
        return arrayList.size();
    }

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object){
                System.out.println("t1启动");
                for (int i = 0; i < 10; i++) {
                    if (i == 5){
                            //=5时 将t1等待 先将锁释放，此时t2获得锁
                            object.notify();//唤醒
                        try {
                            //t1等待 wait  会释放锁资源对象 即object
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    test01.add(i);
                    System.out.println("add：" + i);
                }
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (object){
                System.out.println("t2启动");
                if (test01.size() != 5) {
                    try {
                        //t2等待，释放锁，t1获得锁，开始执行
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                //t1 i=5 时 释放锁，进行等待，此时t2获得锁，执行结束，释放锁，让t1继续执行
                object.notify();
            }
        }, "t2");

        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }
}
