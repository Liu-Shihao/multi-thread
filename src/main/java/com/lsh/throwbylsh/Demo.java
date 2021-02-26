package com.lsh.throwbylsh;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/25 6:22 下午
 * @desc ：
 */
public class Demo {
    public static void main(String[] args) {
        try {
            System.out.println("执行try块中的代码");
            System.out.println(1/0);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("执行catch块中的代码");
        }finally{
            System.out.println("最后执行finally块中的代码");

        }

    }

    public void text() throws InterruptedException,ClassNotFoundException {
        Thread.sleep(1000);
    }

    public void test(){
        try {
            throw new RuntimeException("使用throw关键字抛出一个异常。");
        }catch (RuntimeException e){
            System.out.println("catch");
        }
    }
}
