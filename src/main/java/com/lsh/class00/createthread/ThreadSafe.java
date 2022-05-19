package com.lsh.class00.createthread;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/24 3:32 下午
 * @desc ：使用退出标志退出线程
 *  定义了一个退出标志 exit，当 exit 为 true 时，while 循环退出，exit 的默认值为 false.在定义 exit
 *  时，使用了一个 Java 关键字 volatile，这个关键字的目的是使 exit 同步，也就是说在同一时刻只
 *  能由一个线程来修改 exit 的值。
 */
public class ThreadSafe extends Thread{
    public volatile boolean exit = false;
    @Override
    public void run() {
        while (!exit){
        //do something
        }
    }
}
