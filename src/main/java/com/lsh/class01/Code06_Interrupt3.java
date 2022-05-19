package com.lsh.class01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 2:49 下午
 * @desc ：
 * 在对sleep wait join 设置标志位，会抛出InterruptedException异常
 */
public class Code06_Interrupt3 {

    public static void main(String[] args)  {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread is Interrupt");
                // 查询标记位 返回为false，在抛出InterruptedException异常后，Java会重置标记位
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        //线程开始
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //设置标志位
        t1.interrupt();
    }
}
