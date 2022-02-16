package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 3:26 下午
 * @desc ：
 * t1.stop()  方法结束线程 ，此方法已经被弃用，不建议使用
 * t1.suspend()    线程暂停，此方法已经被弃用
 * t1.resume()     线程回复，此方法已经被弃用
 *
 */
public class Code01_Stop_Suspend_Resume {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("线程执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        t1.stop();

        t1.suspend();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.resume();



    }
}
