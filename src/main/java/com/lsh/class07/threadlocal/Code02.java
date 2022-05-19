package com.lsh.class07.threadlocal;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/5/19 9:31 下午
 * @desc ：ThreadLocal 中的值 是线程独有的
 * ThreadLocal应用场景：Spring声明式事务、切换数据源
 *
 * t2线程 在threadLocal set值为lisi
 * t1线程 获取 threadLocal 中的值 却为 null
 * 即两个线程互不影响
 *
 * 所以可以得出结论：ThreadLocal 中的值 是线程独有的
 * ThreadLocal 源码解读：  即 t1线程读取不到其他线程中的ThreadLocalMap值的
 * set（）方法：
 *   public void set(T value) {
 *         Thread t = Thread.currentThread(); //首先拿到当前线程
 *         ThreadLocalMap map = getMap(t);//获得当前线程自己的ThreadLocal.ThreadLocalMap
 *         if (map != null)
 *             map.set(this, value);
 *         else
 *             createMap(t, value);
 *     }
 *
 *
 *
 */
public class Code02 {

    static ThreadLocal threadLocal  = new ThreadLocal<Person>();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set(new Person("lisi"));
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

        public Person(String name) {
            this.name = name;
        }

        public Person() {
        }
    }
}
