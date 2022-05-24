package com.lsh.class11.forkjoinpool;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 11:17 下午
 * @desc ：ParallelStream  并行流处理  使用的也是forkjoinPool
 */
public class Code03_ParallelStream {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(random.nextInt(1000000));
        }

        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start);
        long start2 = System.currentTimeMillis();
        nums.parallelStream().forEach(Code03_ParallelStream::isPrime);
        long end2 = System.currentTimeMillis();
        System.out.println(end2-start2);

    }

    //判断是否为质数
    public static boolean isPrime(int n){
        for (int i = 2; i < n>>>1; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
