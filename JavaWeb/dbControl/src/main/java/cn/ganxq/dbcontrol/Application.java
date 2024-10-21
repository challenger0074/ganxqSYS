package cn.ganxq.dbcontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微信公众号：Java技术栈
 */
@MapperScan(basePackages = {"cn.ganxq.dbcontrol.mapper"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
