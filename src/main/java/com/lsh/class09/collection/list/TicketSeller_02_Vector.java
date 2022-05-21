package com.lsh.class09.collection.list;

import java.util.List;
import java.util.Vector;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 10:34 下午
 * @desc ：售票问题：
 * 虽然使用的线程安全的Vector容器，但是依然出现超卖问题
 * ArrayIndexOutOfBoundsException: Array index out of range: 0
 */
public class TicketSeller_02_Vector {
    static List<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0){
                    try {
                        //在两个原子操作中间并没有加锁
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了："+ tickets.remove(0));;

                }
            }).start();
        }
    }
}
