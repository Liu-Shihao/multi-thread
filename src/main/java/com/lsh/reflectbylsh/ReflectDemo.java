package com.lsh.reflectbylsh;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/25 7:38 下午
 * @desc ： 学习Java反射机制
 */
public class ReflectDemo {
    /**
     * 获得class对象的三种形式:
     * 1.调用某个对象的 getClass()方法
     * 2.调用某个类的 class 属性来获取该类对应的 Class 对象
     * 3.使用 Class 类中的 forName()静态方法(最安全/性能最好)
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Student student = new Student();
        Class<? extends Student> aClass1 = student.getClass();

        Class<Student> aClass2 = Student.class;

        Class<?> aClass3 = Class.forName("com.lsh.reflectbylsh.Student");


        //当我们获得了想要操作的类的 Class 对象后，可以通过 Class 类中的方法获取并查看该类中的方法和属性。
        //获取类的所有方法信息
        Method[] methods = aClass3.getMethods();
        for (Method method : methods) {
            System.out.println("方法："+method.getName()+"（）");
        }

//        Method[] declaredMethods = aClass3.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            System.out.println("声明的方法"+declaredMethod.toString());
//        }

        /**
         *getFields() 获得某个类的所有的公共（public）的字段，包括父类中的字段。
         * getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
         */
        Field[] fields = aClass3.getFields();
        for (Field field : fields) {
            System.out.println("private字段："+field.toString());
        }
        Field[] declaredFields = aClass3.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("类中所有声明的字段:"+declaredField);

        }
        //获得类的注解
//        Data annotation = aClass3.getAnnotation(Data.class);
//        String s = annotation.staticConstructor();
//        System.out.println("Data.class.staticConstructor():"+s);

        Constructor<?>[] constructors = aClass3.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("构造函数:"+constructor.toString());
        }
        //通过Class对象的newInstance方法创造实例对象
        Student stu1 = (Student)aClass3.newInstance();
        System.out.println(stu1);

        //首先通过class对象获得一个构造方法，在通过构造方法创建实例对象
        Constructor<?> constructor = aClass3.getConstructor(String.class,int.class);
        Student stu2 = (Student)constructor.newInstance("北京大学",703);
        System.out.println(stu2);

    }


}
