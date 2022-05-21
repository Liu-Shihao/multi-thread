package com.lsh.class03.sync;
/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/22 9:17 下午
 * @desc ： synchronized
 * 1. synchronized 锁的是一个对象时，对象不能发生改变，需要final修饰对象不可变
 *  例如：t1线程获得了o对象的锁，开始执行，此时t2线程获取不到锁对象，则永远无法执行
 *  但是 如果o发生了改变 ,此时 t1和t2 会一起执行
 *  所以 需要将synchronized修饰的对象o用final 修饰，设置为不可变对象。
 * 2. synchronized的锁对象不能是基础数据类型，如：String、Integer、Long等
 *
 */
public class Code03_Synchronized {
    public static /*final*/ Object o = new Object();
    static void m(){
        //获得锁对象 o
        synchronized (o){
            while (true){
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"执行m方法");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * m1 方法的锁对象是 s1
     * m2 方法的锁对象是 s2
     * 但是m1和m2却不能同步执行
     */
    static class T{

        static String s1 = "hello";
        static String s2 = "hello";

        static void m1(){
             synchronized (s1){
                 while (true){
                     try {
                         Thread.sleep(2000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println(Thread.currentThread().getName()+"执行m1方法");

                 }

             }
         }
        static void m2(){
             synchronized (s2){
                 while (true){
                     try {
                         Thread.sleep(2000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println(Thread.currentThread().getName()+"执行m2方法");
                 }

             }
         }

    }

    public static void main(String[] args) {
//        new Thread(Code03_Synchronized::m,"t1").start();
//        Thread t2 = new Thread(Code03_Synchronized::m, "t2");
//        o = new Object();
//        t2.start();
        System.out.println("--------");
        Code03_Synchronized code03_synchronized = new Code03_Synchronized();
        new Thread(T::m1,"t1").start();
        new Thread(T::m2,"t2").start();


    }
}
