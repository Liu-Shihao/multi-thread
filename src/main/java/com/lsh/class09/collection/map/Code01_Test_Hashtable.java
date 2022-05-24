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
 *  通过实现发现：ConcueenrtHashMap在多线程下写的效率没有Hashtable/synchronizedMap/高，但是读的效率是最高的
 *
 */
public class Code01_Test_Hashtable {

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
    static Hashtable<UUID, UUID> hashtable = new Hashtable<>();

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("","");


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
                    hashtable.put(keys[j],valus[j]);
                    //由于HashMap的线程不安全，所以测试没有意义
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
        System.out.println(hashtable.size());

        System.out.println(System.currentTimeMillis()-start);


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->{
                for (int i1 = 0; i1 < 1000000; i1++) {
                    hashtable.get(keys[10]);
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
        //581
        //5968

    }
}
