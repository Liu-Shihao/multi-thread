package com.lsh.day06;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 5:04 下午
 * @desc ：CountDownLatch 门栓写法
 */
public class Test01II {

     LinkedList arrayList =  new LinkedList<Integer>();

    public void add(int v){
        arrayList.add(v);
    }

    public int size(){
        return arrayList.size();
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Test01II test01II = new Test01II();
        Thread t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                if (i == 5){
                    countDownLatch1.countDown();
                    try {
                        countDownLatch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                test01II.add(i);
                System.out.println("add "+i);

            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            System.out.println("t2启动");
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2结束");
            countDownLatch2.countDown();
        }, "t2");
        t2.start();
        t1.start();
    }

}
