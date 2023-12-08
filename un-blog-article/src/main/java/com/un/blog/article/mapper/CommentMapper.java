package com.un.blog.article.mapper;

import com.un.blog.entities.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论信息表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-04-22
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> findByArticleId(@Param("articleId") String articleId);

}
