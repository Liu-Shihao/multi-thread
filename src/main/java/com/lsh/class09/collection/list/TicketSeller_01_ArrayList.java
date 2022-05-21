package com.lsh.class09.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 10:34 下午
 * @desc ：售票问题：
 * 使用线程不安全的ArrayList，会出现超卖问题
 * 销售了：null
 * 销售了：null
 * 销售了：null
 * 销售了：null
 * 销售了：null
 * 销售了：null
 * 销售了：null
 * java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
 *
 */
public class TicketSeller_01_ArrayList {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0){
                    try {
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
