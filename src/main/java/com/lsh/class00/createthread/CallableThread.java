package com.lsh.class00.createthread;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/22 10:54 上午
 * @desc ：
 */
public class CallableThread {
     public static void main(String[] args) throws ExecutionException, InterruptedException {
          //创建一个线程池
          ExecutorService pool = Executors.newFixedThreadPool(4);
          // 创建多个有返回值的任务
          ArrayList<Future> futures = new ArrayList<>();
          for (int i = 0; i < 4 ; i++) {
               //Lambda表达式写法
               Callable callable = () -> {
                    System.out.println("call方法执行");
                    return Instant.now();
               };
               // 执行任务并获取 Future 对象
               Future f = pool.submit(callable);
               futures.add(f);
          }
          // 关闭线程池
          pool.shutdown();
          //获取所有的执行结果
          for (Future future : futures) {
               // 从 Future 对象上获取任务的返回值，并输出到控制台
               System.out.println("res:"+ future.get().toString());
          }
     }

}
