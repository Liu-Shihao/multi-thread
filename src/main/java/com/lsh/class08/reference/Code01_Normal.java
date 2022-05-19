package com.lsh.class08.reference;

import java.io.IOException;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 10:07 下午
 * @desc ：
 *  * Java 四种引用：强、软、弱、虚
 *  * 引用：变量  ————>  对象
 *  * 普通引用就是强引用： new（）方法  强引用一定不会被 垃圾回收器回收
 */
public class Code01_Normal {
    public static void main(String[] args) throws IOException {
        M m = new M(); //强引用 垃圾回收器一定不会回收
        m = null; //如果m指向null 则会被垃圾回收，如果不指向null则不会被回收
        System.gc();
        System.in.read();//阻塞主线成
    }
}
