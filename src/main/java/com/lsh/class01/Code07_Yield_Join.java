package com.lsh.class01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/14 2:32 下午
 * @desc ：
 */
public class Code07_Yield_Join {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getState());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
//        t1.join();//join分发可以让t1线程插队，等到t1线程结束后，主线程才会继续
//        Thread.yield();//但是yield方法并不能使主线程等待t1线程结束后才执行。
        System.out.println(t1.getName()+t1.getState());
        System.out.println(Thread.currentThread().getName()+"-END");


    }
}
