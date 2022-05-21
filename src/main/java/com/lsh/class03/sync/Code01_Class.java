package com.lsh.class03.sync;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/17 9:48 上午
 * @desc ：美团面试题
 * 1.请解释一下对象创建的过程
 * 2.DCL与volatile的问题
 * 3.对象在内存中的存储布局（对象与数组的存储不同）
 * 4.对象头具体包括什么？（markword、clisspointer） synchronized锁信息
 * 5.对象怎么定位（直接 间接）
 * 6.对象怎么分配（栈上 - 线程本地 - Eden - Old）
 * 7. Object o = new Object()在内存中占多少字节？
 * 8.Class对象是在堆还是方法区？
 *
 * 默认情况下，偏向锁有个延时，默认是4秒，JVM启动过程会有很多线程竞争，所以默认情况启动时不打开偏向锁，过一段时间之后再；打开偏向锁。
 */
public class Code01_Class {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        //使用synchronized加锁
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
