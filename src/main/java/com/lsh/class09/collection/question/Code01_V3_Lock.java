package com.lsh.class09.collection.question;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法3：使用Lock
 *
 */
public class Code01_V3_Lock {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            for (int i = 65; i <= 90; i++) {
                System.out.print((char) i);
                condition.signal();//信号 叫醒t2
                try {
                    condition.await();//阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signal();//唤醒其他线程 结束程序
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                condition.signal();//信号 叫醒t2
                try {
                    condition.await();//阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signal();//唤醒其他线程 结束程序
        });
        t2.start();
        t1.start();
    }
}
