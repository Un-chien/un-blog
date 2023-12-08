package com.un.blog.article.service;

import com.un.blog.entities.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-04-22
 */
public interface ICommentService extends IService<Comment> {

    Result findByArticleId(String articleId);

    /**
     * 删除评论
     * */
    Result deleteById(String id);


}
