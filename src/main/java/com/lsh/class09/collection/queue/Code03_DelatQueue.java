package com.lsh.class09.collection.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 6:35 下午
 * @desc ：阻塞队列   DelayQueue  按时间进行任务调度
 * 入队的元素需要实现Delayed接口,时间越短的越先执行  通过PriorityQueue实现
 */
public class Code03_DelatQueue {

    static DelayQueue<Delayed> delayeds = new DelayQueue<>();

    static Random random = new Random();

    static class MyTask implements Delayed{
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return runningTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS) > 0) return 1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS) < 0) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask task01 = new MyTask("task01", now + 20000);
        MyTask task02 = new MyTask("task02", now + 5000);
        MyTask task03 = new MyTask("task03", now + 10000);
        MyTask task04 = new MyTask("task04", now + 15000);
        MyTask task05 = new MyTask("task05", now + 100);
        delayeds.add(task01);
        delayeds.add(task02);
        delayeds.add(task03);
        delayeds.add(task04);
        delayeds.add(task05);

        System.out.println(delayeds);
        for (int i = 0; i < 5; i++) {
            System.out.println(delayeds.take());//take（）阻塞
        }

    }
}
