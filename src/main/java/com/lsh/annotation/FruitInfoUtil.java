package com.lsh.annotation;

import java.lang.reflect.Field;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 10:58 上午
 * @desc ：注解处理器
 */
public class FruitInfoUtil {
    /**
     * 获得水果信息
     * @param clazz
     * @throws ClassNotFoundException
     */
    public static void getFruitInfo(Class<?> clazz)  {
        String strFruitProvicer = "供应商信息:";
        //通过反射获取处理注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //isAnnotationPresent()  方法 表示该字段是否存在FruitProvider注解
            if (field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                //注解信息的处理地方
                strFruitProvicer = " 供应商编号:" + fruitProvider.id() + " 供应商名称:"
                        + fruitProvider.name() + " 供应商地址:"+ fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }

    public static void main(String[] args) {
        getFruitInfo(Apple.class);
        getFruitInfo(Banana.class);
    }



}
