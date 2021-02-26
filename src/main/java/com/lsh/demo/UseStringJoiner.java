package com.lsh.demo;

import java.util.StringJoiner;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/25 4:26 下午
 * @desc ： StringJoiner 是基于StringBuilder实现的
 * String类是被final修饰的类，所以String是不可边的
 * StringBuffer是可变的 ，线程安全，效率低
 *
 * StringBuilder是可变的，线程不安全，效率高
 */
public class UseStringJoiner {

    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        IntStream.range(1,10).forEach(i->{
//            sb.append(i+",");
//            if( i < 10){
//                sb.append(",");
//            }
//        });
//         1,2,3,4,5,6,7,8,9,
//        System.out.println("sb:"+sb);


//        StringJoiner sj = new StringJoiner(",");
//        IntStream.range(1,10).forEach(i->sj.add(i+""));
//        System.out.println("sj:"+sj);
        /**
         * prefix    前缀
         * delimiter 间隔符
         * suffix    后缀
         */
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        StringJoiner add = stringJoiner.add("H").add("E").add("L").add("L").add("O");
        System.out.println("add:"+add);
//        System.out.println("add.toString:"+add.toString());
        //间隔符为""表示没有间隔符
        StringJoiner stringJoiner1 = new StringJoiner("");
        StringJoiner add1 = stringJoiner1.add("W").add("o").add("r").add("l").add("d");
        System.out.println("add1:"+add1);

    }



}
