package com.un.blog.question.service;

import com.un.blog.entities.Reply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 回答信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
public interface IReplyService extends IService<Reply> {

    /**
     * 根据问答ID查询所有评论
     */
    Result findByQuestionId(String questionId);

    /**
     * \
     * 批量删除评论
     */
    Result deleteById(String id);
}
