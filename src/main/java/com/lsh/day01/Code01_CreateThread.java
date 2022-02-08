package com.lsh.day01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/8 3:00 下午
 * @desc ：Java创建线程的几种方式
 * 1.继承Thread类，重写run方法。
 * 2.实现Runnable接口，重写run方法。
 * 3.实现Callable接口，重写call方法，有返回值，使用FutureTask类来包装Callable对象。
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

    /**
     * 第三种：实现Callable接口，重写call方法
     * 使用FutureTask作为Thread对象的target创建并启动新线程
     */
    public static class MyCallable implements Callable{

        @Override
        public Object call() throws Exception {
            String str = "MyCallable call 方法执行";
            return str;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //------------------------------------------------
        MyThread myThread = new MyThread();
        myThread.start();
        //------------------------------------------------
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable,"新线程").start();

        //------------------------------------------------
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask,"又返回值线程").start();
        System.out.println(futureTask.get());
        //------------------------------------------------

    }
}
