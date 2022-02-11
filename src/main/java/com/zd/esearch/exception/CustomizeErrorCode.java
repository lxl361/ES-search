package com.zd.esearch.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2002,"你的问题不存在,请换一个试试.."),
    TARGET_PARAM_NOT_FOUND(2001,"未选中任何问题或评论进行回复.."),
    //N0_LOGIN(2003,"用户未登陆..")
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }


}
