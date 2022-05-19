package com.lsh.class08.reference;

import java.lang.ref.SoftReference;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 10:11 下午
 * @desc ： 设置JVM参数：-Xms20M -Xmx20M
 * 软引用：只有系统内存不够使用的时候才会进行回收
 *
 * 应用场景:缓存
 *
 * 备注：
 * byte是java的基本数据类型，存储整型数据，占据1个字节(8 bits)，能够存储的数据范围是-128～+127。
 * Byte是java.lang中的一个类，目的是为基本数据类型byte进行封装。
 * Byte是byte的包装类,就如同Integer和int的关系
 */
public class Code02_Soft {
    public static void main(String[] args) {

        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);//10M
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());//如果m 被回收则打印空值

        //在分配一个数组，堆内存将装不下，这个时候系统会垃圾回收，先回收一次，如果不够，就会把软引用干掉
        byte[] bytes = new byte[1024*1024*11];//11M
        System.out.println(m.get());
        /**
         * 打印结果：
         * [B@5e2de80c
         * [B@5e2de80c
         * null  内存空间不够，导致软引用被回收
         *
         * 如果将软引用直接
         *
         */



    }
}
