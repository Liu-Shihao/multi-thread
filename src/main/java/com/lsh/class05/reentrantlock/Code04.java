package com.lsh.class05.reentrantlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 3:05 下午
 * @desc ：ReadWriteLock 读写锁（共享锁（读锁）和排他锁（写锁））
 */
public class Code04 {
    static ReentrantLock reentrantLock = new ReentrantLock();
    private static int val;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void write(Lock lock,int v){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            val = v;
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 1. 使用 reentrantLock 锁
     * 2. 使用 readLock 锁（多个线程可以共同读）和 reentrantLock 锁
     * @param args
     */
    public static void main(String[] args) {
        Runnable read = () -> read(readLock);
        Runnable write = () -> write(writeLock,new Random().nextInt());
        for (int i = 0; i < 18; i++) {
            new Thread(read).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }

    }
}
