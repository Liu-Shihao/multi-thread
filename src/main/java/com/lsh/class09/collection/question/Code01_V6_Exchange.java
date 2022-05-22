package com.lsh.class09.collection.question;

import java.util.concurrent.Exchanger;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法6 : 使用Exchange解法
 *
 * 同样使用同步队列也可以实现 SynchronousQueue  : 容量为0 先取后放
 */
public class Code01_V6_Exchange {

    private static Exchanger<Object> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 65; i <= 90; i++) {
                    //注意顺序： 先exchanger.exchange("T1"); 阻塞线程
                    exchanger.exchange("T1");
                    System.out.print((char) i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 26; i++) {
                    //同样也是 先阻塞线程
                    exchanger.exchange("T2");
                    System.out.print(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
