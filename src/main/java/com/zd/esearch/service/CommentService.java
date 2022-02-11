package com.zd.esearch.service;

import com.zd.esearch.exception.CustomizeErrorCode;
import com.zd.esearch.exception.CustomizeException;
import com.zd.esearch.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 0003 14:42
 */
@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParentId()==null &&comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

        }
    }
}
