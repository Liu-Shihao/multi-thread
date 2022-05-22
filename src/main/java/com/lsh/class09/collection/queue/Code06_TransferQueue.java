package com.lsh.class09.collection.queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 7:07 下午
 * @desc ：TransferQueue  是个接口，即等待消息被消费后才结束线程
 * 使用其子类 LinkedTransterQueue
 * 特点（与其他阻塞队列的区别）：
 * put（）：将消息放到队列中，离开
 * transfer()：将消息放到队列中，阻塞，等待此条消息被消费
 *
 */
public class Code06_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

        //注意 需要先把消费者线程启动，否则被transfer（）阻塞
        new Thread(()-> {
            try {
                Thread.sleep(5000);
                System.out.println(linkedTransferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()-> {
            try {
                System.out.println("t2投递");
                linkedTransferQueue.transfer("haha");
                System.out.println("t2结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();




    }
}
