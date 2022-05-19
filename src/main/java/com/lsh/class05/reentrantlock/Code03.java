package com.lsh.class05.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 10:46 上午
 * @desc ：公平锁：谁等在前面谁就获得锁 （排队）;非公平锁：谁抢到算谁的
 * Lock lock = new ReentrantLock(true); 获得一个公平锁
 */
public class Code03 extends Thread{
    Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        Code03 reentarntLock03 = new Code03();
        Thread thread1 = new Thread(reentarntLock03);
        Thread thread2 = new Thread(reentarntLock03);
        thread1.start();
        thread2.start();
    }
}
