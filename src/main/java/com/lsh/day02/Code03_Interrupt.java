package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 3:47 下午
 * @desc ：使用interrupt 打断线程
 */
public class Code03_Interrupt {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (Thread.interrupted()) {
                System.out.println("end");
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
