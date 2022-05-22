package com.lsh.class09.collection.question;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法3 升级版本：使用Lock  Condition
 * 使用两个Condition 可以精确的指定线程 阻塞/唤醒
 *
 */
public class Code01_V3_Lock_II {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition t1Condition = lock.newCondition();
        Condition t2Condition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 65; i <= 90; i++) {
                    System.out.print((char) i);
                    t2Condition.signal();//信号 叫醒t2

                        t1Condition.await();//阻塞

                }
                t2Condition.signal();//唤醒其他线程 结束程序
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();//结束程序
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    t1Condition.signal();//唤醒t1
                    t2Condition.await();//阻塞t2
                }
                t1Condition.signal();//唤醒其他线程 结束程序
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();//结束程序
            }


        });
        t2.start();
        t1.start();
    }
}
