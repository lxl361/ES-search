package com.zd.esearch.model;

import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/27 0027 15:09
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;//用户头像地址
}
