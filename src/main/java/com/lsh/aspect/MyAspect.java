package com.lsh.aspect;


import com.lsh.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author     ：LiuShihao
 * @date       ：Created in 2021/2/26 12:12 下午
 * @desc       ：
 * @Aspect 声明一个切面类
 * @Component 注册到Spring容器
 */
@Aspect
@Component
public class MyAspect {

    /**
     * 后置通知
     * 注意 : 注解的切入点表达式
     * 需求 : 记录操作时间,操作人,操作的方法,操作的描述,操作的ip 数据
     * 时间 : new Date()
     * 人,ip : 需要请求对象
     * 方法 : 通过AOP的切入点得到方法名
     * 		JoinPoint 就是目标方法对象
     * 描述 : 通过反射读取注解
     * ===================================
     * 以上属性可以设计成数据库的一种表,用于记录日志信息.
     * 对应的,在创建一个java实体类用于封装数据
     * @param joinPoint
     */

    @After("@annotation(com.lsh.annotation.MyLog)")
    public void afterMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        // 操作时间
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Aspect日志-时间 : "+time);
        // 使用Spring提供的方法,获得HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //模拟session
        HttpSession session = request.getSession();
        session.setAttribute("username","LiuShihao");
        // ip
        String ip = request.getRemoteAddr();
        System.out.println("Aspect日志-ip : "+ip);
        // 操作人用户名
        String name = (String) request.getSession().getAttribute("username");
        System.out.println("Aspect日志-人名 : "+name);
        // 使用joinPoint得到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Aspect日志-方法 : "+methodName);

        //获得切入点的目标类的class对象的这个方法上的我们的自定义的MyLog注解的value
        String desc = joinPoint.getTarget().getClass().getMethod(methodName).getAnnotation(MyLog.class).value();
        System.out.println("Aspect日志-描述 : "+desc);
        System.out.println("-------------------");


    }
}
