package com.lsh.class09.collection.map;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/21 9:38 下午
 * @desc ：容器
 * 物理：数组、链表
 * 逻辑：树、图、队列、堆、栈等
 *                      Collection
 *  List                  Queue               Set
 *  ArrayList                                HashSet
 *  LinkedList                               TreeSet
 *  Vector
 *  CopyOnWriteList
 *
 *  Map：HashMap 、TreeMap
 *
 *
 *  发展历程：
 *  Hashtable --> HashMap -->Collections.synchronizedMap()-->CurrentHashMap
 *
 */
public class Code02_Test_SynchronizedMap {

    private static int CAPACITY = 1000000;
    private static int THREAD_NUM = 100;
    static UUID[] keys = new UUID[CAPACITY];
    static UUID[] valus = new UUID[CAPACITY];
    static {
        for (int i = 0; i < CAPACITY; i++) {
            keys[i] = UUID.randomUUID();
            valus[i] = UUID.randomUUID();
        }
    }
    static Map<UUID, UUID> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {

        /**
         * 100W的数据
         * 100个线程
         * 每个线程取每段的数据
         */
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_NUM];
        //遍历线程
        for (int i = 0; i < THREAD_NUM; i++) {
            int startNum = CAPACITY/THREAD_NUM * i;
            System.out.println(startNum+"-------");
            threads[i] = new Thread(()->{
                for (int j = startNum; j < startNum+CAPACITY/THREAD_NUM; j++) {
                    synchronizedMap.put(keys[j],valus[j]);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(synchronizedMap.size());

        System.out.println(System.currentTimeMillis()-start);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->{
                for (int i1 = 0; i1 < 1000000; i1++) {
                    synchronizedMap.get(keys[10]);
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-start2);
        //683
        //5751



    }
}
