package com.lsh.class00.createthread;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/22 10:45 上午
 * @desc ：
 */
public class ImplRunable implements Runnable {
    @Override
    public void run() {
        System.out.println("实现Runnable接口创建多线程");
    }

    public static void main(String[] args) {
        ImplRunable ImplRunable = new ImplRunable();
        //创建Thread对象,构造参数中传入刚刚创建的Runable子类对象
        Thread thread = new Thread(ImplRunable);
        //调用start方法开启新线程
        thread.start();
//        thread.setDaemon(true); 设置守护线程
//        thread.yield(); 礼让线程
//        thread.join(); 加入线程
//        thread.setPriority(10); 设置优先级
//        thread.sleep(1000); 休眠


        //内部类写法

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现Runnable接口创建多线程");
            }
        }).start();

        /**
         * Runnable 是一个函数式接口，可以使用lambda表达式
         */
        new Thread(()->{
            System.out.println("使用Lambda表达式形式创建多线程");
        }).start();

    }
}
