package com.zd.esearch.exception;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 自定义异常类
 * @date 2022/2/2 0002 16:52
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public  CustomizeException(ICustomizeErrorCode errorCode){
        this.code= errorCode.getCode();
        this.message=errorCode.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
