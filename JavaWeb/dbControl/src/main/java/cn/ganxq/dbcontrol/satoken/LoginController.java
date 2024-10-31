package cn.ganxq.dbcontrol.satoken;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.ganxq.dbcontrol.satoken.entity.LoginForm;
import jakarta.servlet.ServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

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
// 第1步，先登录上
        StpUtil.login(10005);
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
