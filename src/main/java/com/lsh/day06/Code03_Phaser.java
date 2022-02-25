package com.lsh.day06;

import java.util.concurrent.Phaser;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 2:39 下午
 * @desc ：
 */
public class Code03_Phaser implements Runnable{


    public static class MyPhaser extends  Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println(0);
                    return false;
                case 1:
                    System.out.println(1);
                    return false;
                case 2:
                    System.out.println(2);
                    return false;
                case 3:
                    System.out.println(3);
                    return true;
                default:
                    return true;
            }
        }
    }
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        myPhaser.bulkRegister(5);
        myPhaser.arriveAndAwaitAdvance();
        myPhaser.arriveAndDeregister();



    }
    //到达
    public static void arrive(){

    }
    public static void eat(){

    }
    public static void leave(){

    }
    public static void hug(){

    }
    @Override
    public void run() {
        arrive();
        eat();
        leave();
        hug();
    }

}
