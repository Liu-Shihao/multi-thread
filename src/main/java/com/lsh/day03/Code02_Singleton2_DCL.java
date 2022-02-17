package com.lsh.day03;

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
 *
 *
 *
 */
public class Code02_Singleton2_DCL {
    private static  Code02_Singleton2_DCL INSTANTCE;
    private Code02_Singleton2_DCL(){

    }
    public static Code02_Singleton2_DCL getInstance(){
        //在synchronized之前加个判断可以提高效率，因为竞争锁的资源消耗是非常高的，先通过一个判断就可以直接过滤非常多线程，
        // 加了判断不会导致线程上来就竞争所锁，提高效率
        if (INSTANTCE == null){
            //Double Check Lock
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
