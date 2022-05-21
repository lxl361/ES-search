package com.zd.esearch.dto;

import com.zd.esearch.model.User;
import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/5/21 0021 10:02
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
