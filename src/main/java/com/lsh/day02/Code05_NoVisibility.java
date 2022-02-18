package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 7:42 下午
 * @desc ：Visibility  可见性
 * 在这个程序中存在两个问题
 * 1.可见性  ready变量的不可见问题
 * 2.有序性  ready = true;number = 48; 可能存在乱序问题，导致输出为0
 */
public class Code05_NoVisibility {

    private static boolean ready = false;
    private static int number;

    public static void main(String[] args) throws  Exception {
        Thread thread = new Thread(() -> {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        });
        thread.start();
        //上下两行没有依赖关系，理论上可能存在乱序问题，导致输出为0
        ready = true;
        number = 48;
        thread.join();

    }

}
