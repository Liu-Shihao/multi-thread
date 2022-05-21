package com.lsh.class09.collection.list;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 11:12 下午
 * @desc ：COW容器 写时复制：在写入元素的时候会进行复制新数组，写入完成之后将老的容器引用指向新的容器，不会影响读操作
 * 写时加锁；  读操作不加锁
 * 适合读操作多，写操作少的场景
 * 1.CopyOnWriteArrayList
 * 2.CopyOnWriteArraySet
 *
 */
public class Code10_CopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        CopyOnWriteArraySet<Object> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    }
}
