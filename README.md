# daocao

## 1、数据库建立

ums_menu

```sql
CREATE TABLE `ums_menu` (
    `menu_id` bigint NOT NULL COMMENT '主键',
    `parent_id` bigint DEFAULT NULL COMMENT '父id',
    `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名',
    `sort` int(10) unsigned zerofill DEFAULT NULL COMMENT '排序',
    `menu_type` int DEFAULT NULL COMMENT '类型，0：目录，1：菜单，2：按钮',
    `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由路径',
    `component_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
    `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
    `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标',
    `deleted` int DEFAULT NULL COMMENT '是否删除',
    `status` int DEFAULT NULL,
    `creater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `updater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

ums_sys_role_menu

```sql
CREATE TABLE `ums_sys_role_menu` (
    `id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    `menu_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `role_id_menu` (`role_id`),
    KEY `menu_id` (`menu_id`),
    CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `ums_menu` (`menu_id`),
    CONSTRAINT `role_id_menu` FOREIGN KEY (`role_id`) REFERENCES `usm_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

ums_sys_user

```sql
CREATE TABLE `ums_sys_user` (
  `id` bigint NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avator` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `creator` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

ums_sys_user_role

```sql
CREATE TABLE `ums_sys_user_role` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `usm_role` (`role_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `ums_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

usm_role

```sql
CREATE TABLE `usm_role` (
  `role_id` bigint NOT NULL,
  `role_label` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int DEFAULT NULL COMMENT '排序',
  `status` int DEFAULT NULL COMMENT '状态：0可用，1不可用',
  `deleted` int DEFAULT NULL COMMENT '是否删除0：未删除，1已删除',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `creator` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

## 2、Auth模块

- 创建相应的实体类、Mapper
- Service、ServiceImpl



## 3、common模块

定义公共模块

### **定义响应码**

定义前后端判断请求状态的响应码，当然可以定义响应码之外的数据

```java
/**
 * 保存状态码
 */
public class HttpStatus {
    public static final int SUCCESS=200;
    public static final int CREATED=201;
    public static final int ACCEPTED=202;
    public static final int NO_CONTENT=204;
    /**
     * 资源已被移除
     */
    public static final int MOVED_PERM=301;
    public static final int SEE_OTHER=303;
    /**
     * 资源没有被修改
     */
    public static final int NOT_MODIFIED=304;

    /**
     * 参数列表错误
     */
    public static final int BAD_REQUEST=400;
    public static final int UNAUTHORIZED=401;
    public static final int FORBIDDEN=403;
    public static final int NOT_FOUND=404;
    public static final int BAD_METHOD=405;
    public static final int CONFLICT=409;
    public static final int UNSUPPORTED_TYPE=415;
    public static final int ERROR=500;
    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED=501;
    /**
     * 系统警告信息
     */
    public static final int WARN=601;
}
```

### 统一返回结果集

前端返回数据的时候数据格式是JSON，结构是：

```json
{
    code: HttpStatus.SUCCESS,
    data: xxx/对象/数组
    msg: "相应信息"
}
```

code：前端根据code判断请求的状态

msg：根据msg的值做一些提示

data：数据，可以是单个数据，对象或者集合【数组】

```java
package org.xiaoke.common.response;



import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.xiaoke.common.constant.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
 * 统一相应类
 */
@Data
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID=1L;
    /**
     * 状态码
     */
    public static final String CODE_TAG="code";
    /**
     * 返回内容
     */
    public static final String MSG_TAG="msg";
    /**
     * 数据对象
     */
    public static final String DATA_TAG="data";

    /**
     * 初始化一个新创建的AjaxResult对象，使其表示一个空信息
     */
    public Result(){
    }

    /**
     * 初始化一个新创建的AjaxResult对象
     * @param code 状态码
     * @param msg 返回内容
     */
    public Result(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    /**
     * 初始化一个新创建的AjaxResult对象
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public Result(int code,String msg,Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if (ObjectUtil.isNull(data)){
            super.put(DATA_TAG,data);
        }
    }

    /**
     *返回成功信息
     * @return 成功信息
     */
    public static  Result success(){
        return Result.success("操作成功");
    }

    public static Result success(Object data){
        return Result.success("操作成功",data);
    }




    public static  Result success(String msg){
        return Result.success(msg,null);
    }

    /**
     * 返回成功信息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功信息
     */
    public static Result success(String msg,Object data){
        return new Result(HttpStatus.WARN,msg,data);
    }

    /**
     * 返回警告信息
     * @param msg 返回内容
     * @return 警告信息
     */
    public static  Result warn(String msg){
        return Result.warn(msg,null);
    }

    /**
     * 返回警告信息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告信息
     */
    public static Result warn(String msg,Object data){
        return  new Result(HttpStatus.WARN,msg,data);
    }

    /**
     * 返回错误信息
     * @return 错误信息
     */
    public static Result error(){
        return Result.error("操作失败");
    }

    /**
     * 返回错误信息
     * @param msg 返回内容
     * @return 错误信息
     */
    public static Result error(String msg){
        return Result.error(msg,null);
    }

    /**
     * 返回错误信息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误信息
     */
    public static  Result error(String msg,Object data){
        return new Result(HttpStatus.ERROR,msg,data);
    }

    /**
     * 返回错误信息
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误信息
     */
    public static  Result error(int code,String msg){
        return new Result(code,msg,null);
    }

    /**
     * 是否为成功消息
     * @return 结果
     */
    public boolean isSuccess(){
        return Objects.equals(HttpStatus.SUCCESS,this.get(CODE_TAG));
    }

    /**
     * 是否为警告信息
     * @return 结果
     */
    public boolean isWarn(){
        return Objects.equals(HttpStatus.WARN,this.get(CODE_TAG));
    }

    /**
     * 是否为错误信息
     * @return 结果
     */
    public boolean isError(){
        return Objects.equals(HttpStatus.ERROR,this.get(CODE_TAG));
    }

    /**
     * 方便链式调用
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public Result put(String key, Object value){
        super.put(key,value);
        return this;
    }
}
```

### 定义业务异常

```java
package org.xiaoke.common.exception;

import lombok.Getter;

/**
 * 自定义的业务异常，当系统出现异常时，返回该异常给前端，运行时异常抛出
 */
@Getter
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID=1L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    private ServiceException(){}

    private ServiceException(String message){
        this.message=message;
    }
    private ServiceException(String message,int code){
        this.code=code;
        this.message=message;
    }
    public ServiceException setMessage(){
        this.message=message;
        return this;
    }
}
```

上传代码异常：

先拉取仓库的最新代码，再做开发，提交
