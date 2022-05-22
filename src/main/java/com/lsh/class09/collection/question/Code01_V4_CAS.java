package com.lsh.class09.collection.question;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/22 10:26 下午
 * @desc ：两个线程交叉打印A1B2C3D4...Z26
 * 解法4 : CAS解法
 */
public class Code01_V4_CAS {

    enum ReadyToRun {T1,T2};

    //必须使用volatile修饰，保证线程间可见性
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 65; i <= 90; i++) {
                while (r != ReadyToRun.T1){} //等待
                System.out.print((char) i);
                r = ReadyToRun.T2;//更新变量 让T2 执行
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                while (r != ReadyToRun.T2){} //循环等待 会消耗CPU（wait()不会消耗CPU资源）
                System.out.print(i);
                r = ReadyToRun.T1;//更新变量 让T1 执行
            }
        });
        t2.start();
        t1.start();
    }
}
