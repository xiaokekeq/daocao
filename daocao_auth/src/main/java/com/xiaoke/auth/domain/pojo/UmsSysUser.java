package com.xiaoke.auth.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsSysUser implements Serializable {
    @TableId(type= IdType.ASSIGN_UUID)
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private String sex;
    private String avator;
    private String password;
    private Integer status;
    private String creator;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    private String updater;
    @TableField(fill=FieldFill.UPDATE)
    private Date updateTime;
    private String remark;
    @TableLogic
    private Integer deleted;
}
