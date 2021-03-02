package com.lsh.generator.controller;

import com.lsh.generator.entity.User;
import com.lsh.generator.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * 使用EasyCode生成的代码
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-03-02 10:03:32
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

}
