package com.lsh;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/24 5:24 下午
 * @desc ：
 */
@SpringBootTest
public class ThreadTest {

    /**
     * join方法
     */
    @Test
    public void testJoin() throws InterruptedException {
        String name = Thread.currentThread().getName();
        Thread thread2 = new Thread(() -> {
            System.out.println("这时 thread2 执行完毕之后才能执行thread1");
            for (int i = 0; i < 10; i++) {
                System.out.println("线程222正在执行--"+i);
            }
        });
        //  此时为主线程，调用join方法的线程相当于插队的，之前当时正在运行的线程（被插队的线程）需要等待插队的线程执行完后才能继续执行
        for (int i = 0; i < 100; i++) {
            if (i == 5){
                thread2.start();
                thread2.join();
            }
            System.out.println(Thread.currentThread()+"正在执行--"+i);
        }
    }


    @Test
    public void test(){

    }
}
