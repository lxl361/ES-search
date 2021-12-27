package com.zd.esearch.dto;

import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/26 0026 15:06
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
