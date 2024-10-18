package cn.javastack.springboot.mybatisplus.mapper;

import cn.javastack.springboot.mybatisplus.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author ganxq
 * @since 2024-08-21
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    boolean insert2User(Users user);
    public List<Users> getAllUser(@Param("username")String username,@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
    public int getUserCounts(@Param("username") String username);
}
