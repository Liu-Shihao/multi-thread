package com.lsh.class09.collection.queue;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.SynchronousQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 6:53 下午
 * @desc ：SynchronousQueue 同步队列 两个线程传递消息  容量为0  先取再放
 *
 * SynchronousQueue 作用与Exchange相同： 两个线程之间传递消息
 */
public class Code05_SynchronousQueue {
    static Random random = new Random();

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        Exchanger<Object> exchanger = new Exchanger<>();
        System.out.println(synchronousQueue.size());//size 为 0
        new Thread(()->{
            try {
                while (true){
                    System.out.println(synchronousQueue.take());;//阻塞
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                while (true){
                    synchronousQueue.put("t2-"+random.nextInt(100));//使用add会抛出队列已满异常（因为容量为0）
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();



        while (true){
            try {
                synchronousQueue.put("main-"+random.nextInt(100));//
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
