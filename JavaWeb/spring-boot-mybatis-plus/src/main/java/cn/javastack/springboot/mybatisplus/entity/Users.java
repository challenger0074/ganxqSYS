package cn.javastack.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author ganxq
 * @since 2024-08-21
 */
@Getter
@Setter
@TableName("users")
@ApiModel(value = "Users对象", description = "用户信息表")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户唯一标识符")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户登录名")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户电子邮件地址")
    @TableField("email")
    private String email;

    @ApiModelProperty("账户创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("最后更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
