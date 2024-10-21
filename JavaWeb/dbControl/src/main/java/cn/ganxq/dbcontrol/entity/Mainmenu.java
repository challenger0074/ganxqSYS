package cn.ganxq.dbcontrol.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@TableName("mainmenu")
@ApiModel(value = "Mainmenu对象", description = "Represents a main menu with submenus")
public class Mainmenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("path")
    private String path;

    // Initialize the list to avoid NullPointerException
    private List<Submenu> slist=new ArrayList<>() ;

    // Constructor with parameters


    // No-argument constructor
    public Mainmenu() {
    }

    // Override toString, equals, and hashCode methods if necessary
}

