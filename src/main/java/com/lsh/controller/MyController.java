package com.lsh.controller;

import com.lsh.annotation.MyLog;
import com.lsh.model.Order;
import com.mzt.logapi.starter.annotation.LogRecordAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 12:32 下午
 * @desc ：
 */
@Slf4j
@RestController
@RequestMapping("/mylog")
public class MyController {

    public static final String ORDER = "ORDER";

    @MyLog("添加操作")
    @PostMapping("/add")
    public void add(){
        System.out.println("Controller执行添加的业务代码");
    }
    @MyLog("删除操作")
    @DeleteMapping("/del")
    public void del(){
        System.out.println("Controller执行删除的业务代码");
    }
    @MyLog("更新操作")
    @PutMapping("/update")
    public void update(){
        System.out.println("Controller执行更新的业务代码");
    }
    @MyLog("查询操作")
    @GetMapping("/select")
    public void select(){
        System.out.println("Controller执行查询的业务代码");
    }

    /**
     * pefix：是拼接在 bizNo 上作为 log 的一个标识。避免 bizNo 都为整数 ID 的时候和其他的业务中的 ID 重复。比如订单 ID、用户 ID 等
     * bizNo：就是业务的 ID，比如订单ID，我们查询的时候可以根据 bizNo 查询和它相关的操作日志
     * success：方法调用成功后把 success 记录在日志的内容中
     * SpEL 表达式：其中用双大括号包围起来的（例如：{{#order.purchaseName}}）#order.purchaseName 是 SpEL表达式。Spring中支持的它都支持的。比如调用静态方法，三目表达式。SpEL 可以使用方法中的任何参数
     * category:类别  主要是为了对日志做分类，查询方便，支持更多的业务。
     * detail：如果一个操作修改了很多字段，但是success的日志模版里面防止过长不能把修改详情全部展示出来，这时候需要把修改的详情保存到 detail 字段， detail 是一个 String ，需要自己序列化。这里的 #order.toString() 是调用了 Order 的 toString() 方法。 如果保存 JSON，自己重写一下 Order 的 toString() 方法就可以。
     * operator：操作者
     * operatorId：操作者Id
     */
    @LogRecordAnnotation(
            fail = "创建订单失败，失败原因：「{{#_errorMsg}}」",
            category = "MANAGER",
            detail = "{{#order.toString()}}",

            success = "{{#order.purchaseName}}下了一个订单,购买商品「{{#order.productName}}」,下单结果:{{#_ret}}",
            prefix = ORDER, bizNo = "{{#order.orderNo}}")
    @PostMapping("/createOrder")
    public boolean createOrder(Order order) {
        log.info("【创建订单】orderNo={}", order.getOrderNo());
        // db insert order
        // System.out.println(1/0);
        return true;
    }

}
