package cn.javastack.springboot.mybatisplus.controller;


import cn.javastack.springboot.mybatisplus.entity.Users;
import cn.javastack.springboot.mybatisplus.model.QueryInfo;
import cn.javastack.springboot.mybatisplus.service.IUsersService;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

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
    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return 创建成功的响应
     */
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        try {
            userService.createUser(user); // 调用服务层方法保存用户
            return ResponseEntity.ok("用户创建成功");
        } catch (Exception e) {
            // 处理异常并返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("创建用户失败: " + e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/find")
    public Users findUsers() {
        Users users = new Users();
        /*users.setId(10);*/
        users.setEmail("123");
        users.setUsername("张三30");
        users.setPassword("dfd24");
        return userService.getById(users);
    }
    @ResponseBody
    @GetMapping("/list")
    public String getUserList(QueryInfo queryInfo){
        System.out.println(queryInfo);
        int userCounts = userService.getUserCounts(queryInfo.getQuery());
        List<Users> users = userService.userList(queryInfo);
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",userCounts);
        res.put("data",users);
        System.out.println("总条数"+userCounts);
        String jsonString = JSON.toJSONString(res);
        return jsonString;

    }
}
