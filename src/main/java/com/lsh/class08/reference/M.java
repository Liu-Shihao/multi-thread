package com.lsh.class08.reference;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 10:06 下午
 * @desc ：在gc的时候会调用对象的finalize()方法
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");//如果打印了finalize，说明此对象被垃圾回收
    }
}
