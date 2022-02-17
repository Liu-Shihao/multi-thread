package com.lsh.day03;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/17 10:29 上午
 * @desc ：懒汉式单例
 * 等到调用的时候在创建实例，
 * 在多线程时容易出现创建出多个对象问题
 * 可以通过加synchronized解决问题，synchronized加在方法上，会导致锁的内容太多。
 * 如果synchronized加在方法上加在方法内部，则还是会出现问题：
 * INSTANTCE为空，此时两个进程都要创建对象，第一个获得锁，创建了对象后，释放锁，此时另一个线程可以获得锁又创建了一个对象。
 *
 * 所以可以使用DCL（double checked locking ）双重检查锁解决
 *
 *
 */
public class Code02_Singleton2 {
    private static  Code02_Singleton2 INSTANTCE;
    private Code02_Singleton2(){

    }
    public static synchronized Code02_Singleton2 getInstance(){

        if (INSTANTCE == null){
            synchronized (Code02_Singleton2.class){
                //容易出现new出多个对象
                try {
                    //线程睡眠，便于显示创建多个对象问题，要解决这个问题可以使用synchronized
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANTCE = new Code02_Singleton2();
            }
        }

        return INSTANTCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                Code02_Singleton2 instance = getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
