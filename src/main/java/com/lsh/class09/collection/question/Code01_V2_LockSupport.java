package com.lsh.class09.collection.question;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法2：使用 LockSupport.park() 和 LockSupport.unpark() 解决
 */
public class Code01_V2_LockSupport {

    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        System.out.println("A:"+(int)'A');//A:65
        System.out.println("Z:"+(int)'Z');//Z:90

        /**
         * 思路：
         * 1. t1、t2开始运行(t2开始阻塞)
         * 2. t1 首先打印 A ，之后唤醒t2，阻塞自己（t1）
         * 3. t2 打印 1，然后唤醒t1,自己再次阻塞（t2）
         */
         t1 = new Thread(() -> {
             for (int i = 65; i <= 90; i++) {
                 System.out.print((char)i);
                 LockSupport.unpark(t2);//唤醒t2
                 LockSupport.park();//阻塞t1
             }
         });

         t2 = new Thread(() -> {
             for (int i = 1; i <= 26; i++) {
                 LockSupport.park();//阻塞t2
                 System.out.print(i);
                 LockSupport.unpark(t1);//唤醒t1
             }
         });

         t1.start();
         t2.start();

    }
}
