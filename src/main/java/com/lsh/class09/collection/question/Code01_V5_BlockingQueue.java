package com.lsh.class09.collection.question;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法5 : 使用阻塞队列解法
 * 创建两个容量为1的阻塞队列 利用put 和 take 方法的阻塞特性
 */
public class Code01_V5_BlockingQueue {

    private static ArrayBlockingQueue q1 = new ArrayBlockingQueue<>(1);
    private static ArrayBlockingQueue q2 = new ArrayBlockingQueue<>(1);



    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (int i = 65; i <= 90; i++) {
                    q2.put((char) i);
                    System.out.print(q1.take());
                }
            }catch (Exception e){}

        }).start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(q2.take());
                    q1.put(i);
                }
            }catch (Exception e){}

        }).start();
    }
}
