package com.lsh.class10.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 12:02 上午
 * @desc ：FixedThreadPool 固定线程数线程池
 * 核心线程数和最大线程数 都是固定的且相同
 *
 * 如何确定线程数大小：
 * Nthread = Ncpu * Ucpu * (1+W/C)
 * Nthread 线程数  可以通过Runtime.getRuntime().availableProcessors()获得
 * Ncpu CPU核数
 * Ucpu 期望CPU利用率 （0~1）
 * W wait 等待时间
 * C compute 计算时间
 *
 * 最终的结果 还是需要进行压测
 *
 *
 *
 */
public class Code06_FixedThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newFixedThreadPool(4);

        //使用main线程计算1-20W 之间的质数
        long start = System.currentTimeMillis();
        List<Integer> ans1 = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(ans1.size());

        System.out.println("---------");
        long start2 = System.currentTimeMillis();
        Future<List<Integer>> submit1 = service.submit(() -> getPrime(1, 50000));
        Future<List<Integer>> submit2 = service.submit(() -> getPrime(50001, 100000));
        Future<List<Integer>> submit3 = service.submit(() -> getPrime(100001, 150000));
        Future<List<Integer>> submit4 = service.submit(() -> getPrime(150001, 200000));
        List<Integer> integers = submit1.get();
        List<Integer> integers1 = submit2.get();
        List<Integer> integers2 = submit3.get();
        List<Integer> integers3 = submit4.get();
        long end2 = System.currentTimeMillis();
        System.out.println(end2 -start2);
        System.out.println(integers.size()+integers1.size()+integers2.size()+integers3.size());

        service.shutdown();


    }

    //判断是否为质数
    public static boolean isPrime(int n){
        for (int i = 2; i < n>>>1; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static List<Integer> getPrime(int start, int end){
        ArrayList<Integer> integers = new ArrayList<>();
        for (; start <= end; start++) {
            if (isPrime(start)) integers.add(start);
        }
        return integers;
    }
}
