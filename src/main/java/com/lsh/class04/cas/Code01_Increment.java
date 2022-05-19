package com.lsh.class04.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/22 9:58 下午
 * @desc ：
 *
 * AtomicInteger本身方法都是原子性的，底层通过cas实现 乐观锁
 * LongAdder内部使用分段锁，所以效率高
 */
public class Code01_Increment {
    static Long count1 = 0L;
    static AtomicLong count2=  new AtomicLong(0);
    static LongAdder count3 = new LongAdder();

    static final Object lock = new Object();

    static int testTime = 10000;
    static int threadNum = 1000;


    static void m1(){
          for (int i = 0; i < testTime; i++) {
              synchronized (lock){
                  count1++;
              }
          }
    }
    static void m2(){
          for (int i = 0; i < testTime; i++) {
              count2.incrementAndGet();
          }
    }
    static void m3(){
          for (int i = 0; i < testTime; i++) {
              count3.increment();
          }
    }
    public static void main(String[] args) throws InterruptedException {
        long start1 = System.currentTimeMillis();
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(Code01_Increment::m1,"t"+i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long l1 = System.currentTimeMillis() - start1;
        System.out.println("synchronized    :"+l1+" :"+count1);


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(Code01_Increment::m2,"t"+i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long l2 = System.currentTimeMillis() - start2;
        System.out.println("AtomicLong      :"+l2+" :"+count2.get());

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(Code01_Increment::m3,"t"+i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long l3 = System.currentTimeMillis() - start3;
        System.out.println("LongAdder       :"+l3+" :"+count3);
    }
}
