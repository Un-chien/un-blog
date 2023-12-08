package com.un.blog.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.un.blog.article.req.ArticleListREQ;
import com.un.blog.entities.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Article findArticleAndLabelById(String id);

    /**
     * 新增文章标签中间表数据
     */
    boolean saveArticleLable(@Param("articleId") String articleId, @Param("lableIds") List<String> listIds);

    /**
     * 通过文章id删除中间表数据
     */
    boolean deleteArticleLable(@Param("articleId") String articleId);

    /**
     * 查询分类或标签下文章
     */
    IPage<Article> findListByLabelIdOrCategoryId(IPage<Article> page, @Param("req") ArticleListREQ req);

    /**
     * 查询每个分类下的文章数量
     */
    List<Map<String,Object>>selectCategoryTotal();

}
