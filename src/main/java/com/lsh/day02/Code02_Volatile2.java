package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 4:17 下午
 * @desc ：volatile保障可见性
 *
 * 不使用volatile修饰的变量running，则主线程修改为false 后，另一个线程不会看见，所以不会停止
 * 而使用volatile修饰的变量running_volatile，主线程修改为false 后，另一个线程看见，则停止
 *
 */
public class Code02_Volatile2 {

    private  static  boolean running = true;
    private  static volatile  boolean running_volatile = true;

    public static void m(){
        System.out.println("start");
        while (running_volatile){
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

    }
}
