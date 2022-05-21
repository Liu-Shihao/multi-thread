package com.lsh.class09.collection.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 11:24 下午
 * @desc ：
 *
 * ConcurrentLinkedQueue 链表实现的  无界队列  现成安全的
 * offer == add
 * poll 取数据 并移除
 * peek 查看数据 不移除
 *
 *
 */
public class Code01_ConcurrentQueue {

    public static void main(String[] args) {
        Queue<Object> concurrentLinkedDeque = new ConcurrentLinkedQueue<>();
    }
}
