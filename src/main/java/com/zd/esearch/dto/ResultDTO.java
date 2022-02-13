package com.zd.esearch.dto;

import com.zd.esearch.exception.CustomizeErrorCode;
import com.zd.esearch.exception.CustomizeException;
import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 0003 14:27
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO errOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errOf(CustomizeErrorCode errorCode) {
        return errOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errOf(CustomizeException e) {
        return errOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }


}
