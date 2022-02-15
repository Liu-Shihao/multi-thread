package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/15 10:58 下午
 * @desc ：线程状态
 */
public class Code05_ThreadState {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            //线程执行中  RUNNABLE状态
            System.out.println("2："+Thread.currentThread().getState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //new 创建出线程，未调用start方法 是NEW状态
        System.out.println("1："+t1.getState());
        t1.start();
        //调用sleep方法 ，线程为TIMED_WAITING状态
        System.out.println("3："+t1.getState());
        //t1.join(); 等待t1线程结束
        t1.join();
        //线程任务完成后为TERMINATED状态
        System.out.println("4："+t1.getState());
    }
}
