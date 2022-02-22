package com.lsh.day04_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/22 9:58 下午
 * @desc ：AtomidXXX本身方法都是原子性的，但是不能保证多个方法连续调用是原子性的。
 */
public class Code01_AtomicInteger {
      AtomicInteger count=  new AtomicInteger(0);

      void m(){
          for (int i = 0; i < 10000; i++) {
              count.incrementAndGet();
          }
      }
    public static void main(String[] args) {
        Code01_AtomicInteger code01_atomicInteger = new Code01_AtomicInteger();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(code01_atomicInteger::m,"t"+i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        System.out.println(code01_atomicInteger.count.get());
    }



}
