package com.lsh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 12:09 下午
 * @desc ：
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {


    /**
     * 操作记录
     * @return
     */
    String value() default "";

}

