package com.zd.esearch.mapper;

import com.zd.esearch.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}
