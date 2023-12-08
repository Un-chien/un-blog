package com.un.blog.question.service.impl;

import com.un.blog.entities.QuestionLabel;
import com.un.blog.question.mapper.QuestionLabelMapper;
import com.un.blog.question.service.IQuestionLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题标签中间表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
@Service
public class QuestionLabelServiceImpl extends ServiceImpl<QuestionLabelMapper, QuestionLabel> implements IQuestionLabelService {

}
