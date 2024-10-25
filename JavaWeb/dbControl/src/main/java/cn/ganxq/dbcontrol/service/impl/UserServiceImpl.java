package cn.ganxq.dbcontrol.service.impl;

import cn.ganxq.dbcontrol.entity.User;
import cn.ganxq.dbcontrol.mapper.UserMapper;
import cn.ganxq.dbcontrol.model.QueryInfo;
import cn.ganxq.dbcontrol.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author ganxq
 * @since 2024-08-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper mapper;

    @Override
    public boolean save1(User entity) {

        return mapper.insert2User(entity);
    }

    @Override
    public int getUserCounts(String username) {
        // 模糊查询
        int userCounts = mapper.getUserCounts("%"+username+"%");
        return userCounts;
    }

    @Override
    public List<User> userList(QueryInfo queryInfo) {

        //从哪里开始查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("$$$$$$$$$$$$$$$$$"+pageStart);
        List<User> userList = mapper.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
       int SIZE = queryInfo.getPageSize();
        System.out.println("$$$$$$$$$$$$$$$$$"+SIZE);
        return userList;
    }

    @Override
    public void createUser(User user) {
        mapper.insert(user);//mybatisplus封装
    }

    // 其他方法


}
