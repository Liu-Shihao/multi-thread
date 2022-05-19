package com.lsh.day03_sync_volatile;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/18 11:07 下午
 * @desc ：TODO
 * 1. 使用dto.getPhone()可以成功加锁、
 * 2. 使用"123123"普通固定字符串可以成功加锁、
 *
 * 3. 使用拼接的String加锁失败 String s = dto.getPhone() + dto.getProductType();
 */
public class Code04_Sync_String {
    public static void m1(Dto dto){
        String s1 = dto.getPhone();
        String s2 = dto.getPhone() + dto.getProductType();
        String lock2 = "123123";
        synchronized (s2){
            try {
                System.out.println("当前线程："+Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Dto dto1 = new Dto("123", "1");
        Dto dto2 = new Dto("123", "1");
        String phoneAndProduct = dto1.getPhone() + dto1.getProductType();
        String s1 = dto1.getPhone() + dto1.getProductType();
        String s2 = dto2.getPhone() + dto2.getProductType();
        System.out.println(dto1.getPhone() == dto2.getPhone());//true
        System.out.println(s1==s2);//false
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append1 = stringBuffer.append(dto1.getPhone()).append(dto1.getProductType());
        StringBuffer append2 = stringBuffer.append(dto1.getPhone()).append(dto2.getProductType());
        System.out.println(append1 == append2);//true
        new Thread(()->{
            m1(dto1);
        },"t1").start();
        new Thread(()->{
            m1(dto2);
        },"t2").start();
    }

    public static class Dto{
        public static String phone;
        public static String productType;


        public Dto(String phone,String product) {
            this.phone = phone;
            this.productType = product;
        }

        public static String getPhone() {
            return phone;
        }
        public static String getProductType() {
            return productType;
        }
    }
}
