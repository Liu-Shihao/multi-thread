package com.lsh.class10.threadpool;

import java.util.concurrent.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/23 10:06 下午
 * @desc ：线程池
 * Executor  <-  ExecutorService <- ThreadPoolExecutor
 * execute(Runnanle)
 *
 * Runnable
 * Callable Future
 * FutureTask  实现了RunnableFuture，RunnableFuture继承extends Runnable, Future<V>
 *
 * submit() 返回 Future
 *
 */
public class Code01_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(500);
            return 1000;
        });

        new Thread(futureTask).start();
        System.out.println(futureTask.get());//阻塞方法

        //使用线程池执行submit()方法 返回Future对象
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        Future<Integer> submit = newFixedThreadPool.submit(() -> {//异步
            Thread.sleep(500);
            return 1;
        });
        System.out.println(submit.get());
        System.out.println(submit.isDone());//是否结束   true

    }


}
