package com.zd.esearch.dto;

import com.zd.esearch.model.Question;
import com.zd.esearch.model.User;
import lombok.Data;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 在question对象中增加user对象
 * @date 2021/12/30 0030 9:36
 */
@Data
public class QuestionDTO extends Question {
    private User user;
}
