package com.lsh.day06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 3:56 下午
 * @desc ：
 * LockSupport.park();阻塞线程
 * LockSupport.unpark(t); 恢复线程
 */
public class Code05_LockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                if (i == 5 ){
                    //LockSupport.park(); 阻塞
                    LockSupport.park();
                }
            }
        });
        t.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 seconds");
        LockSupport.unpark(t);
    }
}
