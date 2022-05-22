package com.lsh.class09.collection.question;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法7 : 使用TransferQueue 解法：生产者线程会阻塞知道数据被消费者取出
 *
 */
public class Code01_V7_TransferQueue {

    private static LinkedTransferQueue<Object> transferQueue = new LinkedTransferQueue<>();

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                for (int i = 65; i <= 90; i++) {
                    //注意顺序： t1先放（ABCDEFG...），阻塞。。。 等到数据被T2后取出 才会继续运行
                    transferQueue.transfer((char) i);
                    System.out.print(transferQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 26; i++) {
                    //注意： t2 先取 ，后放（1234567），然后阻塞
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
