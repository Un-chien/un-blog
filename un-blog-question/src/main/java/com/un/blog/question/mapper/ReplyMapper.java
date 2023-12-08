package com.un.blog.question.mapper;

import com.un.blog.entities.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回答信息表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
public interface ReplyMapper extends BaseMapper<Reply> {


    List<Reply> findByQuestionId(@Param("questionId") String questionId);
}
