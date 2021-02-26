package com.lsh.controller;

import com.lsh.annotation.MyLog;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 12:32 下午
 * @desc ：
 */
@RestController
@RequestMapping("/mylog")
public class MyController {

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

}
