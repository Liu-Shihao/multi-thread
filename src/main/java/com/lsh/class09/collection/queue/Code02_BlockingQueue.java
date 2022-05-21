package com.lsh.class09.collection.queue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 11:20 下午
 * @desc ：阻塞队列
 *
 * 阻塞方法：
 * put（）  添加，如果队列满，则阻塞
 * take（） 取出，如果队列为空，则阻塞
 *
 * 所以 BlockingQueue 天生实现了生产者/消费者模型
 *
 * 1. ArrayBlockingQueue  有界的
 * 2. LinkedBlockingQueue 无界的
 * 3. DelayQueue 实现时间上的排序
 * 4. SynchronousQueue 用于两个线程之间传递内容 容量为0  先取后放
 * 5. TransferQueue 可以传递任务 多个
 *
 */
public class Code02_BlockingQueue {
    static LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>();
    static Random random = new Random();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while (true){
                    try {
                        linkedBlockingQueue.put("aaaa");
                        Thread.sleep(random.nextInt(10000));
                    } catch (InterruptedException e) {
                    }
                }

            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    while (true){
                        Object take = linkedBlockingQueue.take();
                        System.out.println("take:"+take);
                    }

                } catch (InterruptedException e) {
                }
            }).start();
        }




    }
}
