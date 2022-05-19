package com.lsh.class08.reference;

import java.lang.ref.WeakReference;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 10:32 下午
 * @desc ：Java 弱引用 ： 只要有gc 就会回收
 * 应用场景：用在容器中 （WeakHashMap）
 *
 * ThreadLocal
 * 内存泄漏：内存永远不会被回收
 */
public class Code03_Weak {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
        /**
         * com.lsh.class08.reference.M@5e2de80c
         * finalize 发生gc
         * null     弱引用被回收
         */

        /**
         * 当创建出一个ThreadLocal对象时，是一个强引用 mThreadLocal 指向ThreadLocal对象
         * 而在ThreadLocal的源码中，使用的是一个 ThreadLocalMap ,这个Map的Key就是ThreadLocal对象本身，但是这个key的引用 ，使用的是一个弱引用指向ThreadLocal对象
         * 如果mThreadLocal 强引用没有了，则ThreadLocalMap对象此时还有Key的弱引用，是可以直接被垃圾回收掉的，
         * 但如果Kye的引用是一个强引用的话，则虽然mThreadLocal的引用没有了，但是ThreadLocal对象还是会存在一个强引用，导致无法被回收，从而导致内存泄漏问题
         *
         * 第二点：
         * ThreadLocal 不用了，此时被回收掉
         * 则此时的key值为null，但是value值还存在，且无法被访问到，
         * 如果越积累越多则还是会发生内存泄漏问题
         * 所以在使用ThreadLocal结束后，一定要将key remove掉
         *
         *
         */
        ThreadLocal<M> mThreadLocal = new ThreadLocal<>();
        mThreadLocal.set(new M());
        mThreadLocal.remove();

    }
}
