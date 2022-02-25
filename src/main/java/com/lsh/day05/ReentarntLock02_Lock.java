package com.lsh.day05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 10:23 上午
 * @desc ：ReentrantLock 可以替代synchronized
 * lock和 synchronized 是一样的
 * 用法：加锁lock.lock() ,手动解锁lock.unlock();
 * 如果synchronized遇到异常，jvm会自动释放锁；但是lock需要手动释放锁；
 * lock还有lock.tryLock()的用法：尝试锁定
 * lock.tryLock(5,TimeUnit.SECONDS) 等待5秒钟之内获得锁，如果没有获得锁则不执行
 *
 *
 */
public class ReentarntLock02_Lock {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //必须在finally块中 解锁
            lock.unlock();

        }
    }
    void m2(){
        boolean tryLock = false;
       try {
           tryLock = lock.tryLock(5, TimeUnit.SECONDS);
           lock.lockInterruptibly();
           if (tryLock){
               //成功获得锁，执行
               System.out.println("m2..");
           }else {
               System.out.println("m2未获得锁");
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally {
           if (tryLock){
               lock.unlock();
           }

       }
    }

    public static void main(String[] args) {
        ReentarntLock02_Lock reentarntLock02 = new ReentarntLock02_Lock();
        new Thread(reentarntLock02::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentarntLock02::m2).start();
    }
}
