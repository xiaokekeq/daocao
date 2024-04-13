package com.xiaoke.auth.domain.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsMenu implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    private Long menuId;
    private Long parentId;
    private String menuName;
    private Integer sort;
    private Integer menuType;
    private String path;
    private String componentPath;
    private String perms;
    private String icon;
    private Integer deleted;
    private Integer status;
    private String creator;
    private String updater;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
