package com.lsh.day03;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/17 10:23 上午
 * @desc ：饿汉式单例
 * 将实例提前new出来，空间换时间
 */
public class Code02_Singleton {

    private static final Code02_Singleton INSTANT = new Code02_Singleton();

    //将构造方法私有，别人无法调用
    private Code02_Singleton(){

    }
    //对外提供公开的获取实例的方法
    public static Code02_Singleton  getInstant(){
        return INSTANT;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Code02_Singleton instant = Code02_Singleton.getInstant();
        Code02_Singleton instant2 = Code02_Singleton.getInstant();
        System.out.println(instant ==instant2 );//true
    }


}
