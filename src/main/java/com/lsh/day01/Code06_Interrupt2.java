package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 2:49 下午
 * @desc ：
 * 设置标记位 + 查询标记位并清除标记位
 */
public class Code06_Interrupt2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (;;){
                //Thread.interrupted() 查询并清除标志位
                if (Thread.interrupted()){
                    System.out.println("Thread is interrupt");
                    System.out.println(Thread.currentThread().isInterrupted());
                    //结束线程，如果发现设置过标志位打断，则 break
                }
            }
        });
        //线程开始
        t1.start();

        Thread.sleep(1000);
        //设置标志位
        t1.interrupt();
        //查询当前线程标记位并重置标记位，虽然是t1调用的，但是当前线程还是主线程
//        t1.interrupted();
    }
}
