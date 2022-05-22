package com.lsh.class09.collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 6:47 下午
 * @desc ：优先级队列：PriorityQueue 有序  内部通过二叉树实现  堆排序
 * 小根堆：小的在上
 * 大跟堆：大的在上
 */
public class Code04_PriorityQueue {
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    static PriorityQueue<Integer> bigPriorityQueue = new PriorityQueue<>(new MyComparator());

    public static void main(String[] args) {
        priorityQueue.add(10);
        priorityQueue.add(7);
        priorityQueue.add(20);
        priorityQueue.add(1);
        priorityQueue.add(15);
        System.out.println(priorityQueue);

        bigPriorityQueue.add(10);
        bigPriorityQueue.add(7);
        bigPriorityQueue.add(20);
        bigPriorityQueue.add(1);
        bigPriorityQueue.add(15);
        System.out.println(bigPriorityQueue);






    }

    static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
