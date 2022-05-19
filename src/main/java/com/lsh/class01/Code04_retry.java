package com.lsh.class01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/15 12:08 下午
 * @desc ：retry: 使用方法 break retry;
 */
public class Code04_retry {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;

    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(()-> System.out.println("hello world!"));
//        executorService.shutdown();
//        executorService.awaitTermination(1, TimeUnit.MINUTES);
//        print(1 << COUNT_BITS);
//        print(CAPACITY);
//        System.out.println(ctlOf(RUNNING, 0));
//        print(-536870912);

        retry:
        for (;;) {
            for (;;) {
                System.out.println("inner");
                //break retry; 内层循环结束并且直接跳出外层循环
                break retry;
            }
//            System.out.println("2");
        }
//        System.out.println("3");

        AtomicInteger atomicInteger = new AtomicInteger(1);
        int i = atomicInteger.get();
        new Thread(() ->{
            atomicInteger.incrementAndGet();
        }).start();

        System.out.println(i);
        System.out.println(atomicInteger.get());



    }

    public static void print(int n){
        for (int i = 31; i >=0 ; i--) {
            System.out.print( (n & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
