package com.lsh.class06.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 7:31 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 一个线程打印 ABCD..
 * 一个线程打印 1234..
 */
public class Test02_Print {
    static Thread t1 = new Thread();
    static Thread t2 = new Thread();
    public static void main(String[] args) {
//        System.out.println("1 :"+Integer.valueOf('1'));//1 :49
//        System.out.println("a :"+Integer.valueOf('a'));//a :97
//        System.out.println("A :"+Integer.valueOf('A'));//A :65
//        System.out.println((char)98);//b

        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                int c = 65 + i;
                System.out.print((char) c);
                LockSupport.park();
                LockSupport.unpark(t2);
            }
        });
         t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();

    }
}
