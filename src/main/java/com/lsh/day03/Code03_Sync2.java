package com.lsh.day03;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 3:59 下午
 * @desc ：
 */
public class Code03_Sync2 {
    private static Object  o = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+" start!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" end!");
                }

            }).start();

        }
    }
}
