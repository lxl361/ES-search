package com.zd.esearch.controller;

import com.zd.esearch.dto.CommentCreateDTO;
import com.zd.esearch.dto.ResultDTO;
import com.zd.esearch.exception.CustomizeErrorCode;
import com.zd.esearch.model.Comment;
import com.zd.esearch.model.User;
import com.zd.esearch.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 评论控制器
 * @date 2022/2/3 0003 8:39
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @PostMapping("/comment")
    public Object  post(@RequestBody CommentCreateDTO commentCreateDTO,
                        HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errOf(CustomizeErrorCode.N0_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setType(commentCreateDTO.getType());
        comment.setLikeCount(0L);
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
