package com.lsh.class02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 4:17 下午
 * @desc ：volatile保障可见性
 *
 * 不使用volatile修饰的变量running，则主线程修改为false 后，另一个线程不会看见，所以不会停止
 * 而使用volatile修饰的变量running_volatile，主线程修改为false 后，另一个线程看见，则停止
 *
 * volatile 引用数据类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 *
 *
 */
public class Code02_Volatile2 {

    private  static  boolean running = true;
    private  static volatile  boolean running_volatile = true;

    public static void m(){
        System.out.println("start");
        while (running){
            //注意：如果循环内执行println，则线程会停止，因为println方法是synchronized
//            System.out.println("running");
        }
        System.out.println("end ");
    }

    public static void main(String[] args) {
        new Thread(Code02_Volatile2::m,"f").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running_volatile = false;
        running = false;

    }
}
