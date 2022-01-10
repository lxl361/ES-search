package com.zd.esearch.service;
import com.zd.esearch.dto.PaginationDTO;
import com.zd.esearch.dto.QuestionDTO;
import com.zd.esearch.mapper.QuestionMapper;
import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.Question;
import com.zd.esearch.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 组装question和user
 * @date 2021/12/30 0030 9:54
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<=1){
            page=1;
        }
        if (page>=totalPage){
            page=totalPage;
        }
        Integer offset=size * (page -1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }


    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        if (page<=1){
            page=1;
        }
        if (page>=totalPage){
            page=totalPage;
        }
        Integer offset=size * (page -1);
        List<Question> questions = questionMapper.listById(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }
}
