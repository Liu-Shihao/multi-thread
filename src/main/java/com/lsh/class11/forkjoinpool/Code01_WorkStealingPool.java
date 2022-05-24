package com.lsh.class11.forkjoinpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/24 10:07 下午
 * @desc ：WorkStealingPool   工作窃取线程池 通过ForkJoinPool实现
 * 每个线程都有自己的队列，当自己的任务执行完了，就会去取其他线程队列中的任务执行
 */
public class Code01_WorkStealingPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();
    }
}
