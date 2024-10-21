package cn.ganxq.dbcontrol.service.impl;

import cn.ganxq.dbcontrol.entity.Users;
import cn.ganxq.dbcontrol.mapper.UsersMapper;
import cn.ganxq.dbcontrol.model.QueryInfo;
import cn.ganxq.dbcontrol.service.IUsersService;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    UsersMapper mapper;

    @Override
    public boolean save1(Users entity) {

        return mapper.insert2User(entity);
    }

    @Override
    public int getUserCounts(String username) {
        // 模糊查询
        int userCounts = mapper.getUserCounts("%"+username+"%");
        return userCounts;
    }

    @Override
    public List<Users> userList(QueryInfo queryInfo) {

        //从哪里开始查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("$$$$$$$$$$$$$$$$$"+pageStart);
        List<Users> userList = mapper.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
       int SIZE = queryInfo.getPageSize();
        System.out.println("$$$$$$$$$$$$$$$$$"+SIZE);
        return userList;
    }

    @Override
    public void createUser(Users user) {
        mapper.insert(user);//mybatisplus封装
    }

    // 其他方法


}
