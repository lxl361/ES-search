package com.zd.esearch.controller;

import com.zd.esearch.dto.CommentDTO;
import com.zd.esearch.dto.ResultDTO;
import com.zd.esearch.exception.CustomizeErrorCode;
import com.zd.esearch.mapper.CommentMapper;
import com.zd.esearch.model.Comment;
import com.zd.esearch.model.User;
import com.zd.esearch.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 0003 8:39
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @PostMapping("/comment")
    public Object  post(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errOf(CustomizeErrorCode.N0_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setType(commentDTO.getType());
        comment.setLikeCount(0L);
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
