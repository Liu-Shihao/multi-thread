package com.lsh.class07.threadlocal;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 9:31 下午
 * @desc ：本地线程
 * 通过次程序解释 多个线程修改同一个变量导致前后读取的值不相同，通过Code02 使用ThreadLocal解决
 * t1 线程打印 p.name = zhangsan
 * 但是t2线程在t1线程执行过程中将p.name值修改为lisi。
 * 则导致t1线程最后打印的是lisi
 */
public class Code01 {
    volatile static Person p = new Person();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();


    }

    static class Person{
        public String name = "zhangsan";

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
