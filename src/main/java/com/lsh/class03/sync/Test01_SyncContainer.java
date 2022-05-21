package com.lsh.class03.sync;
import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/20 2:54 下午
 * @desc ：面试题:两个生产者、一个队列、十个消费者 实现阻塞调用,拥有get put getCount 方法
 *
 * 使用notifyAll（）、wait（）方法实现
 */
public class Test01_SyncContainer {


    public static class Container<T>{
       private final LinkedList list = new LinkedList<T>();
       private final Integer MAX = 10;//最多10个元素
       private int count;

       public synchronized void set(T t){
           while (list.size() == MAX){
               try {
                   this.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
           list.add(t);
           ++count;
           this.notifyAll();//通知消费者线程进行消费
       }

       public synchronized T get(){
           T t = null;
           while (list.size() == 0){
               try {
                   this.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           t = (T) list.removeFirst();
           count-- ;
           this.notifyAll();//通知生产者进行生产
           return t;
       }

    }

    public static void main(String[] args) {
        Container<Integer> container = new Container<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                System.out.println(container.count);
            },"c"+i).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 2; i++) {
            new Thread(()->{
                container.set((int)(Math.random() * 10));
                System.out.println(container.count);
            },"p"+i).start();
        }


    }
}
