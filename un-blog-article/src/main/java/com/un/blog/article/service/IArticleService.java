package com.un.blog.article.service;

import com.un.blog.article.req.ArticleListREQ;
import com.un.blog.article.req.ArticleREQ;
import com.un.blog.article.req.ArticleUserREQ;
import com.un.blog.entities.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */
public interface IArticleService extends IService<Article> {

    Result queryPage(ArticleREQ req);

    Result findArticleAndLabelById(String id);

    Result updateOrSave(Article article);

    Result updateStatus(String id, int code);

    Result findListByUserId(ArticleUserREQ req);

    Result updateThumhup(String id, int count);

    Result updateViewCount(String id);

    Result findListByLabelIdOrCategoryId(ArticleListREQ req);

    Result getArticleTotal();

    Result selectCategoryTotal();
}
