package com.lsh.class10.threadpool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/23 10:39 下午
 * @desc ：
 */
public class Code02_CompletableFuture {

    public static void main(String[] args)throws InterruptedException {

        long start = System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceJD());

        //allOf() 现成全部完成 ; anyOf()任意一个线程完成
        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        CompletableFuture.supplyAsync(()->priceTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price" + str )
                .thenAccept(System.out::println);

        try {
            //将主线程阻塞，打印异步线程结果
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Double priceTM() {
        delay();
        return 1.0;
    }
    private static Double priceTB() {
        delay();
        return 3.0;
    }
    private static Double priceJD() {
        delay();
        return 2.5;
    }



    private static void delay() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(7000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
