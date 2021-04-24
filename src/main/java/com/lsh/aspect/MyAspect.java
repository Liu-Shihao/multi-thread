package com.lsh.aspect;

import com.lsh.annotation.MyLog;
import com.lsh.entity.LogRecord;
import com.lsh.repository.LogRecordRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    LogRecordRepository logRecordRepository;

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

//    @After("@annotation(com.lsh.annotation.MyLog)")
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
        // 操作人用户名  人名是从session中获取的值
        String name = (String) request.getSession().getAttribute("username");
        System.out.println("Aspect日志-人名 : "+name);
        // 使用joinPoint得到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Aspect日志-方法 : "+methodName);

        //获得切入点的目标类的class对象的这个方法上的我们的自定义的MyLog注解的value
        /**
         * 注意：此处通过方法名 找到的方法 只是无参的  无法找到有参的同样方法名的 有参方法
         */
        String desc = joinPoint.getTarget().getClass().getMethod(methodName).getAnnotation(MyLog.class).value();
        System.out.println("Aspect日志-描述 : "+desc);
        System.out.println("-------------------");
    }

    @After("@annotation(com.lsh.annotation.MyLog)")
    public void afterMethod2(JoinPoint joinPoint) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        LogRecord logRecord = new LogRecord();
        // 操作时间
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("方法调用时间 : "+time);

        // 使用Spring提供的方法,获得HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getRemoteAddr();
        System.out.println("方法调用IP地址 : "+ip);

        // 操作人用户名  人名是从session中获取的值
        String name = (String) request.getSession().getAttribute("username");
        System.out.println("方法调用者 : "+name);

        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        System.out.println("URL :"+requestURL+"; "+" URI: "+requestURI);

        // 使用joinPoint得到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("调用的方法名："+methodName);


        Method[] desc = joinPoint.getTarget().getClass().getMethods();

        String editContent = "";
        List<Method> methods = Arrays.asList(desc);
        for (Method method : methods) {

//            System.out.println("方法名："+method.getName());
            if (method.getName().equals(methodName)){
                editContent =   method.getAnnotation(MyLog.class).value();
            }
        }
        //通过反射 获得切入点的目标类的class对象的这个方法上的我们的自定义的MyLog注解的value
        System.out.println("调用方法操作内容："+editContent);

        //获得 方法的参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg.toString());
            Class<?> aClass = arg.getClass();
            Field[] fields = aClass.getFields();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //注意：设置字段可访问， 否则无法访问private修饰的变量值
                declaredField.setAccessible(true);
                //注意：这里需要指定对象   才能获取对应的值
//                System.out.println("字段名2:"+declaredField.getName()+"--》"+declaredField.get(arg));
                if ("operator".equals(declaredField.getName())){
                    logRecord.setOperator((String)declaredField.get(arg));
                }
            }
        }
//        System.out.println("方法请求的参数："+s);
        logRecord.setUrl(requestURL);
        logRecord.setCreateTime(time);
        logRecord.setIpAddress(ip);
        logRecord.setEditContent(editContent);
        logRecordRepository.save(logRecord);
    }


}
