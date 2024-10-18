package cn.javastack.springboot.mybatisplus.service;

import cn.javastack.springboot.mybatisplus.entity.Users;
import cn.javastack.springboot.mybatisplus.model.QueryInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ganxq
 * @since 2024-08-21
 */

public interface IUsersService extends IService<Users> {

    public boolean save1(Users entity);

    public int getUserCounts(String username);

    public List<Users> userList(QueryInfo queryInfo);

    public void createUser(Users user); // 添加创建用户的方法
}
