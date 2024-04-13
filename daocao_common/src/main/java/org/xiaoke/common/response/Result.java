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
