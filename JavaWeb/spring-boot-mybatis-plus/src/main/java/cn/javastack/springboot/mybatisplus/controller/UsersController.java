package cn.javastack.springboot.mybatisplus.controller;


import cn.javastack.springboot.mybatisplus.entity.Users;
import cn.javastack.springboot.mybatisplus.service.IUsersService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ganxq
 * @since 2024-08-22
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService userService;

    @ResponseBody
    @GetMapping("/save")
    public boolean getUserInfoById() {
        Users users = new Users();

        users.setId(14);
        users.setEmail("123");
        users.setUsername("张三30");
        users.setPassword("dfd24");
        return userService.save1(users);
    }

    @ResponseBody
    @GetMapping("/find")
    public Users findUsers() {
        Users users = new Users();

        users.setId(10);
        users.setEmail("123");
        users.setUsername("张三30");
        users.setPassword("dfd24");
        return userService.getById(users);
    }
}
