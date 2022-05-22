package com.lsh.class09.collection.question;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法1：wait（） 线程等待、notify（）线程唤醒、 wait与notify的使用必须和synchronized一起
 * 不能使用sleep（） 无法释放锁资源
 *
 * 无法保证两个线程的执行先后：
 *  1.使用volatile Boolean变量  使t2先阻塞：
 *
 */
public class Code01_V1_Wait_Notify_Sync {

    private static volatile Boolean t1IsStart = false;


    public static void main(String[] args) {

        final Object o = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (o) {

                for (int i = 65; i <= 90; i++) {
                    System.out.print((char) i);
                    try {
                        o.notify();//唤醒t2
                        t1IsStart = true;//t1先打印输出后，打印t2线程, 注意：在t1阻塞之前将bool变量修改为true
                        o.wait();   //t1阻塞，让出锁资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                while (!t1IsStart){
                    //t2 没有运行 先把自己阻塞
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 1; i <= 26; i++) {
                    try {
                        System.out.print(i);
                        o.notify();//唤醒t1
                        o.wait();//t2 阻塞

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//最后唤醒线程结束程序，否则程序不会结束
            }
        });
        //无法保证那个线程在前，可以定义volatile Boolean 变量
        t2.start();
        t1.start();
    }
}
