package com.lsh.class06.juc;

import java.util.concurrent.Exchanger;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 3:43 下午
 * @desc ：Exchanger 交换器
 * 两个线程之间交换数据
 */
public class Code05_Exchange {
    static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t1").start();

        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t2").start();

    }
}
