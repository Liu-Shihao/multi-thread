package com.lsh.day03_sync_volatile;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/17 10:45 上午
 * @desc ：DCL 双重检查锁 Double Check Lock
 * 确保多线程不会重复创建对象
 * 很多开源框架都会这么使用
 *
 * 另外，面试题：DCL要不要加volatile？ 结论：需要！
 *
 * new 对象产生的指令：
 * 0 new #2 <java/lang/Object>      申请内存空间 设置默认值
 * 3 dup
 * 4 invokespecial #1 <java/lang/Object.<init>>   设置初始值
 * 7 astore_1   建立连接
 * 8 return
 *
 * 分析：
 * 0. INSTANTCE为空
 * 1. t1线程new出对象，创建对象 到内存产生五个汇编码
 * 2. 在t1创建线程的时候，汇编码执行发生乱序，设置初始值和建立连接 两个指令发生乱序：这个时候对象创建出来，但是此时为默认值
 * 3.t2线程此时来获取实例对象INSTANTCE，INSTANTCE已经创建了，所以直接拿走使用，但是此时的对象为默认值！
 *
 * volatile 可以禁止指令重排序
 */
public class Code02_Singleton2_DCL {

    //不加volatile会存在对象逸出问题
    private static volatile   Code02_Singleton2_DCL INSTANTCE;

    /**
     * 构造方法私有化，隐藏构造方法，不允许外界调用
     */
    private Code02_Singleton2_DCL(){

    }

    /**
     * 对外提供实例对象
     *
     * 1.不加synchronized，在多线程情况下，如果两个线程都判断了实例为空，那么两个线程都会创建一个对象，就会导致两个对象，不是单例模式。
     * 2.如果没有DCL：
     *   Synchronized没有加在方法上，加在了方法内部：
     *   此时如果两个线程都判断了实例对象为空，但是只有一个线程获得了锁，另一个线程则会等待锁，此时第一个线程创建对象完成释放锁，
     *   那么第二个线程依然会获得锁并创建对象。所以需要DCL（双重检查）
     * 3.如果没有Volatile：
     *   当第一个线程判断实例为空，此时会创建对象。但是在创建对象的过程中，发生了指令重排序，
     *   已经建立了对象的引用，但是此时对象还未初始化，
     *   另一个线程此时来获得实例，发现实例已经创建，直接拿走用了（此时对象还未真正初始化完成，这就是对象逸出了）
     * @return
     */
    public static Code02_Singleton2_DCL getInstance(){
        //在synchronized之前加个判断可以提高效率，因为竞争锁的资源消耗是非常高的，先通过一个判断就可以直接过滤非常多线程，
        // 加了判断不会导致线程上来就竞争所锁，提高效率
        if (INSTANTCE == null){
            //双重检查 Double Check Lock
            synchronized (Code02_Singleton2_DCL.class){
                if (INSTANTCE == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANTCE = new Code02_Singleton2_DCL();
                }
            }
        }
        return INSTANTCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Code02_Singleton2_DCL instance = Code02_Singleton2_DCL.getInstance();
                System.out.println(instance.hashCode());
            }).start();

        }
    }
}
