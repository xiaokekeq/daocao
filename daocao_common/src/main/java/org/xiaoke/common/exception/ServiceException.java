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
