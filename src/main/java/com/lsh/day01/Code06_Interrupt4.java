package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 3:09 下午
 * @desc ：interrupt不能中断正在竞争锁的线程，如果想要打断正在竞争锁的线程，可以使用lock.lockInterruptibly();方法
 *
 * sleep 方法不会释放锁
 */
public class Code06_Interrupt4 {

    public static final Object o = new Object();

    public static void main(String[] args)  {

        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
            System.out.println("t2 end!");
        });
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

//        ReentrantLock lock = new ReentrantLock();
//        lock.lockInterruptibly();

    }
}
