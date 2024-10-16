package cn.javastack.springboot.mybatisplus.mapper;

import cn.javastack.springboot.mybatisplus.entity.Mainmenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ganxq
 * @since 2024-10-15
 */
public interface MainmenuMapper extends BaseMapper<Mainmenu> {
    public List<Mainmenu> getMainMenus();
}
