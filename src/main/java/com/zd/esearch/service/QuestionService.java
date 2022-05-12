package com.zd.esearch.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zd.esearch.dto.PaginationDTO;
import com.zd.esearch.dto.QuestionDTO;
import com.zd.esearch.exception.CustomizeErrorCode;
import com.zd.esearch.exception.CustomizeException;
import com.zd.esearch.mapper.QuestionExtMapper;
import com.zd.esearch.mapper.QuestionMapper;
import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.Question;
import com.zd.esearch.model.QuestionExample;
import com.zd.esearch.model.User;
import com.zd.esearch.util.PageInfoUtil;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PageInfo<QuestionDTO> list(Integer page, Integer size){
        PageHelper.startPage(page, size);
        PageInfo<Question> questionPageInfo = new PageInfo<>(questionMapper.selectByExample(null),size);
        return getquestionDTOPageInfo(questionPageInfo);
    }

    public PageInfo<QuestionDTO> getquestionDTOPageInfo(PageInfo<Question> pageInfo){
        //使用PageInfoUtil将entity转为dto
        PageInfo<QuestionDTO> questionDTOPageInfo = PageInfoUtil.pageInfo2DTO(pageInfo, QuestionDTO.class);
        //添加新增的属性
        for (QuestionDTO questionDTO : questionDTOPageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey((questionDTO.getCreator()));
            questionDTO.setUser(user);
        }
        return questionDTOPageInfo;
    }

    public PageInfo<QuestionDTO> list(Long userId, Integer page, Integer size){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        PageHelper.startPage(page,size);
        PageInfo<Question> questions=new PageInfo<Question>(questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(page, size)));
        return getquestionPageInfoUserId(questions);

    }

    public PageInfo<QuestionDTO> getquestionPageInfoUserId(PageInfo<Question> pageInfo){
        //使用PageInfoUtil将entity转为dto
        PageInfo<QuestionDTO> questionDTOPageInfo = PageInfoUtil.pageInfo2DTO(pageInfo, QuestionDTO.class);
        //添加新增的属性
        for (QuestionDTO questionDTO : questionDTOPageInfo.getList()) {
            User user = userMapper.selectByPrimaryKey((questionDTO.getCreator()));
            questionDTO.setUser(user);
        }
        return questionDTOPageInfo;
    }


    public QuestionDTO getById(Long id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey((question.getCreator()));
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insertSelective(question);//使用insertSelective解决默认值0不生效的问题
        }else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
//        Question question = questionMapper.selectByPrimaryKey(id);
//        Question updateQuestion = new Question();
//        updateQuestion.setViewCount(question.getViewCount()+1);
//        QuestionExample questionExample = new QuestionExample();
//       questionExample.createCriteria().andIdEqualTo(id);
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
        //questionMapper.updateByExampleSelective(updateQuestion, questionExample);
    }
}
