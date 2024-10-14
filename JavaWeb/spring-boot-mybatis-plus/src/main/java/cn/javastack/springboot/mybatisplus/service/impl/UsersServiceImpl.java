package cn.javastack.springboot.mybatisplus.service.impl;

import cn.javastack.springboot.mybatisplus.entity.Users;
import cn.javastack.springboot.mybatisplus.mapper.UsersMapper;
import cn.javastack.springboot.mybatisplus.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
