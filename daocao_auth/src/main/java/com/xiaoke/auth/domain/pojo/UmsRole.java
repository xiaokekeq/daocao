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
public class UmsRole implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    private Long role_id;
    private String roleLabel;
    private String roleName;
    private String sort;
    private Integer status;
    @TableLogic
    private Integer deleted;
    private String remark;
    private String creator;
    private String updater;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
