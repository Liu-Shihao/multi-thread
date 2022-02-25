package com.lsh.day06;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 5:04 下午
 * @desc ：LockSupport 写法
 */
public class Test01III {

     LinkedList arrayList =  new LinkedList<Integer>();

    public void add(int v){
        arrayList.add(v);
    }

    public int size(){
        return arrayList.size();
    }

    static Thread t1 = new Thread();
    static Thread t2 = new Thread();
    public static void main(String[] args) {
        Test01III test01II = new Test01III();

         t2 = new Thread(() -> {
            System.out.println("t2启动");
            LockSupport.park();
            System.out.println("t2结束");

            LockSupport.unpark(t1);
        }, "t2");

         t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                if (i == 5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                test01II.add(i);
                System.out.println("add "+i);
            }
        }, "t1");



        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

}
