package cn.ganxq.dbcontrol.satoken;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.ganxq.dbcontrol.entity.User;
import cn.ganxq.dbcontrol.satoken.entity.LoginForm;
import cn.ganxq.dbcontrol.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    IUserService userService;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello Shiro!");
        return "Hello Shiro!";
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "please login!";
    }
    @GetMapping("/doLogin")
    public SaResult doLogin(LoginForm loginForm, ServletResponse response) {
        System.out.println("用户：" + loginForm);
        //根据用户名从数据库中查询
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginForm.getUsername()));
        if(user == null){
            return SaResult.error("用户名不存在");
        }
        if(!user.getPassword().equals(loginForm.getPassword())){
            return SaResult.error("密码错误");
        }

// 第1步，先登录上
        StpUtil.login(user.getId());
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，返回给前端
        return SaResult.data(tokenInfo);

    }

    /*@RequestMapping("/doLogin")
    public void doLogin(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            System.out.println("用户：" + username + ",登录成功！");
        } catch (Exception e) {
            System.out.println("登录异常" + e.getMessage());
        }
    }*/

}
