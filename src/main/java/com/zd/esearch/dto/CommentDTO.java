package com.zd.esearch.dto;

import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 0003 8:55
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;

}
