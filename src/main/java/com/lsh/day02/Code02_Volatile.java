package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 3:40 下午
 * @desc ：使用volatile结束线程
 * 线程的可见性，存在一个变量，当一个线程修改了这个变量的值，另一个线程是不会看见的。
 * 如果需要变量所有线程可见，则需要使用volatile变量修饰。
 * 使用volatile变量结束线程
 */
public class Code02_Volatile {

    private static volatile boolean running = true;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            long i = 0L;
            while (running){
                if (running){
                    i++;
                }
            }
            System.out.println("end and i = "+i);//747676944   956064220   1081245078
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;
    }
}
