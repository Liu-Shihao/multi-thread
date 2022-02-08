package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/8 3:00 下午
 * @desc ：Java创建线程的几种方式
 * 1.继承Thread类，重写run方法
 * 2.实现Runnable接口，重写run方法
 * 3.实现Callable接口，重写call方法，有返回值，使用FutureTask类来包装Callable对象
 */
public class Code01_CreateThread {

    /**
     * 第一种：继承Thread类，重写run方法。
     */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run 方法执行");
        }
    }

    /**
     * 第二种：实现Runnable接口，重写run方法。需要将runnable 接口作为 Thread 的 入参 来创建Thread对象
     */
    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("MyRunnable run 方法执行");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("=================");
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable,"新线程").start();
    }
}
