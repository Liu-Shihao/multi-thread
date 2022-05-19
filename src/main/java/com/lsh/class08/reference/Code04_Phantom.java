package com.lsh.class08.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 10:54 下午
 * @desc ：Java 虚引用 用于管理堆外内存（虚引用的值无法通过get（）获取）
 * 是给写JVM（虚拟机）的人用的
 * 虚引用即传入对象和队列，当对象被回收时，该对象会被放到队列中通知，通常用在JVM层级编码，有相关堆外内存的使用，因为对象一旦被回收，会被放到队列中，此时调用底层的free方法。
 *
 * NIO DirectByteBuffer 直接内存（堆外内存） 不被JVM虚拟机管理的内存，
 * Unsafe 类 可以直接分配内存 回收内存
 */
public class Code04_Phantom {
    private static final LinkedList<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();


    public static void main(String[] args) {
        //虚引用：两个参数，一个是引用对象，第二个参数是队列类型
        //创建一个M对象 被虚引用，如果这个对象被回收，则会将这个对象放入到队列中通知
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(()->{
            while (true){
                LIST.add(new byte[1024 * 1024]);//设置JVM参数 -Xms20M -Xmx20M
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get());//虚引用的值无法通过get（）获取
            }


        }).start();

        new Thread(()->{

            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll!= null){
                    System.out.println("虚引用对象被回收");
                }

            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
