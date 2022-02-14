package com.lsh.day01;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/8 3:41 下午
 * @desc ：常用线程池体系结构
 *
 * AbstractExecutorService类的invokeAny方法，会执行多个任务，但是只返回第一个结果，返回之后会调用cancel方法依次中断线程，如果有线程睡眠，会报中断异常。
 * 
 * 如果线程2不睡眠 ，则正常打印  111  222
 * 如果线程2睡眠，则值打印111，使用trycatch捕获会发现线程2抛出了java.lang.InterruptedException: sleep interrupted异常
 *
 * 方法上抛出throws InterruptedException一定是阻塞的，因为只有线程阻塞在唤醒的时候才会出现中断异常
 *
 *
 */
public class Code02 {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Integer>> callableArrayList = Lists.newArrayList(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.println("打印：111");
                        return 1;
                    }
                },
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
//                        System.out.println("打印：222");

                        try {
                            //此处睡眠1秒
                            Thread.sleep(1000);
                            System.out.println("打印：222");
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        return 2;
                    }
                });

        Integer integer = executorService.invokeAny(callableArrayList);
        System.out.println(integer);

    }


}
