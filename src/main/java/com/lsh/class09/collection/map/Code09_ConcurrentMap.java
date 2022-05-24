package com.lsh.class09.collection.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 11:05 下午
 * @desc ：多线层高并发情况下多使用的容器：
 * 1.ConcurrentHashMap   哈希表实现的 通过CAS实现的，没有ConcurrentTreeMap结构，因为CAS实现红黑树结构非常困难，所以使用跳表实现，即ConcurrentSkipListMap
 * 2.ConcurrentSkipListMap   使用跳表实现的，高并发且排序
 *
 * TreeMap 红黑树 排序
 *
 */
public class Code09_ConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        ConcurrentSkipListMap<Object, Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<Object> concurrentSkipListSet = new ConcurrentSkipListSet<>();


    }
}
