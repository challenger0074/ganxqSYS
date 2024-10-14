package cn.javastack.springboot.mybatisplus;

import cn.javastack.springboot.mybatisplus.controller.UsersController;
import cn.javastack.springboot.mybatisplus.entity.Users;
import cn.javastack.springboot.mybatisplus.mapper.UsersMapper;

import cn.javastack.springboot.mybatisplus.service.IUsersService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
class AutoApplicationTests {

  @Autowired
    private IUsersService userService;

    @Autowired
    UsersController usersController;
    @Autowired
   UsersMapper usersMapper;
@Test
    void test1() {
        Users users = new Users();

        users.setId(123);
        users.setEmail(".000.");
        users.setUsername("张三0");
        users.setPassword("dfd2");
        boolean flag=userService.save(users);
        System.out.println(flag);
    }


@Test
    void test2() {
        Users users = new Users();

        users.setId(15);
        users.setEmail("889");
        users.setUsername("mkj");
        users.setPassword("dfr2");
       usersMapper.insert2User(users);
    }

    @Test
    void test() {
        Users users = new Users();

        users.setId(15);
        users.setEmail("889");
        users.setUsername("mkj");
        users.setPassword("dfr2");
        Users users1 = usersController.findUsers();
        String jsonString = JSONObject.toJSONString(JSONObject.toJSON(users1));
        System.out.println(jsonString);
    }
}
