package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/8 3:00 下午
 * @desc ：Java创建线程的几种方式
 * 1.创建Java线程的第一种方式：继承Thread类，重写run方法
 */
public class Code01_CreateThread {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run 方法执行");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
