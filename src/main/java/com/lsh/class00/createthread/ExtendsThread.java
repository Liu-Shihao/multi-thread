package com.lsh.class00.createthread;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/22 10:40 上午
 * @desc ： 继承Thread类
 */
public class ExtendsThread  extends Thread{
    @Override
    public void run() {
        System.out.println("继承Thread创建多线程");
    }

    public static void main(String[] args) {
        ExtendsThread myThread = new ExtendsThread();
        myThread.start();
    }
}
