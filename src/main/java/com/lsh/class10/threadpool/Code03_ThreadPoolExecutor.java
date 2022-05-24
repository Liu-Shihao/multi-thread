package com.lsh.class10.threadpool;

import java.util.concurrent.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/23 11:06 下午
 * @desc ：线程池分两种
 * 1. ThreadPoolExecutor
 * 2. ForkJoinPool  ：分解汇总的任务，用很少的线程执行很多的任务（子任务）
 *
 * ThreadPoolExecutor的七个参数：
 * corePoolSize： 核心线程数
 * maximumPoolSize：最大线程数 线程数最大能够扩展数量
 * keepAliveTime： 线程存活时间  线程超过一定时间不工作则关闭线程释放资源。注意：核心线程默认不会被回收掉
 * TimeUtil：时间单位
 * BlockingQueue：阻塞队列  ArrayBlockingQueue有界度列  LinkedBlockingQueue Integer.MAX_VALUE 21亿相当于无界
 * ThreadFactory：线程工厂
 * RejectedExecutionHandler：拒绝策略
 *  1.AbortPolicy  抛出异常
 *  2.DiscardPolicy 扔掉 不抛异常
 *  3.DiscardOldestPolicy  扔掉排队时间最长的
 *  4.CallerRunsPolicy  哪个调用的哪个线程取执行
 *  5.自定义拒绝策略
 *
 * 线程池执行流程：
 * 1. 任务过来 启动线程执行，corePoolSize
 * 2. 任务过来，但是核心线程已满，则任务提交到阻塞队列中，等待执行  BlockingQueue
 * 3. 任务队列已满，还有新任务，则增加新线程执行，maximumPoolSize
 * 4. 如果最大线程数也满了，则执行线程池的拒绝策略。 implements RejectedExecutionHandler
 *
 * 线程池维护的两个集合：
 *  1.线程的集合
 *  2.任务的集合
 *
 *
 */
public class Code03_ThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

    }

    //自定义线程拒绝策略
    class MyRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //TODO 投递MQ
            //重新投递任务队列中 判断任务队列中的任务数量是否小于定义的队列容量
            if (executor.getQueue().size() < 10000){
                executor.getQueue().offer(r);
            }
        }
    }
}
