package cn.javastack.springboot.mybatisplus.service;

import cn.javastack.springboot.mybatisplus.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ganxq
 * @since 2024-08-21
 */

public interface IUsersService extends IService<Users> {

   public boolean save1(Users entity) ;
}
