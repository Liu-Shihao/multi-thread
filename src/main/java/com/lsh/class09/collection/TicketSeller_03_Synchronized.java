package com.lsh.class09.collection;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 10:34 下午
 * @desc ：售票问题：
 * 使用了synchronized加锁，解决超卖问题，但是效率不高
 */
public class TicketSeller_03_Synchronized {
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    synchronized (tickets){
                        if (tickets.size()<=0)break;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了："+ tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
