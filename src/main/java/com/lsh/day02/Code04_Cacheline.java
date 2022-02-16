package com.lsh.day02;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 4:39 下午
 * @desc ：缓存一致性协议       Cacheline 缓存行 64字节
 * long类型是64位 ，8字节  ,所以8 个long类型是一个缓存行
 * MESI (因特尔制定)
 * 缓存行越大，局部性空间效率越高，但读取时间越慢
 * 缓存行越小，局部性空间效率越低，但读取时间越快
 * 取折中值，目前多用64字节
 *
 *
 *
 * @Contended 只有1.8起作用，需要添加jvm参数：-XX:-RestrictContended
 */
public class Code04_Cacheline {
    private static class T {
        private  long  a,b,c,d,e,f,g;
//        @Contended
        public long x = 0L;
        private  long  h,i,j,k,l,m,n;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
//        t1.join();
//        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
