package com.lsh.class10.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 */
public class Code06_FixedThreadPool {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newFixedThreadPool(2);
    }
}
