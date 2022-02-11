package com.zd.esearch.dto;

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
}
