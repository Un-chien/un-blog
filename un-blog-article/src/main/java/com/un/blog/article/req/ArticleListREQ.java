package com.un.blog.article.req;

import com.un.blog.entities.Article;
import com.un.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询分类下文章通用对象")
public class ArticleListREQ extends BaseRequest<Article> {

    @ApiModelProperty(value = "分类id")
    private String categoryId;
    @ApiModelProperty(value = "标签id")
    private String labelId;

}
