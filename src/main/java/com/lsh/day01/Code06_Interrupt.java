package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 2:30 下午
 * @desc ：打断线程
 * 设置标记位 + 查询标记位
 *
 * t1.interrupt();
 *
 * Thread.currentThread().isInterrupted()
 * t1.isInterrupted()
 *
 * Thread.interrupted()
 *
 * interrupt()  打断某个线程（设置标志位）
 * isInterrupted() 查询某个线程是否被打断过（查询标志位）
 * interrupted()  查询当前线程是否被打断过，并重置打断标志
 *
 */
public class Code06_Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (;;){
                //Thread.currentThread().isInterrupted() 查询标志位
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Thread is interrupt");
                    System.out.println(Thread.currentThread().isInterrupted());
                    //结束线程，如果发现设置过标志位打断，则 break
                    break;
                }
            }
        });
        //线程开始
        t1.start();

        Thread.sleep(1000);
        //设置标志位
        t1.interrupt();
    }
}
