package com.zd.esearch.exception;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 0002 16:52
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public  CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
