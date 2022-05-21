package com.lsh.class09.collection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 10:34 下午
 * @desc ：售票问题：
 * 使用 ConcurrentLinkedQueue 实现，线程安全，
 * ConcurrentLinkedQueue的原子性是通过CAS实现的，不是synchronized实现的
 * 提示：在多线程开发中，可以多考虑使用Queue，少使用List
 * 注意：
 * CAS和Synchronized 两个效率的高低不一定
 */
public class TicketSeller_04_ConcurrentLinkedQueue {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    String poll = tickets.poll();//如果队列为空，则返回空
                    if (poll == null) break;
                    else  System.out.println("销售了："+ poll);


                }
            }).start();
        }
    }
}
