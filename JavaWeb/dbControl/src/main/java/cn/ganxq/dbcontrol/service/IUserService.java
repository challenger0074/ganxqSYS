package cn.ganxq.dbcontrol.service;

import cn.ganxq.dbcontrol.entity.User;
import cn.ganxq.dbcontrol.model.QueryInfo;
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

public interface IUserService extends IService<User> {

    public boolean save1(User entity);

    public int getUserCounts(String username);

    public List<User> userList(QueryInfo queryInfo);

    public void createUser(User user); // 添加创建用户的方法
}
