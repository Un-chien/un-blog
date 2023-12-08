package com.un.blog.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.un.blog.article.req.LableREQ;
import com.un.blog.entities.Category;
import com.un.blog.entities.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */
public interface LabelMapper extends BaseMapper<Label> {

    IPage<Label> queryPage(IPage<Label> page, @Param("req") LableREQ req);


}
