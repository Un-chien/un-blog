package com.un.blog.article.req;

import com.un.blog.entities.Article;
import com.un.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "文章查询通用对象" ,description = "文章查询条件")
@Data
public class ArticleREQ extends BaseRequest<Article> {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "状态 0: 已删除, 1:未审核，2:审核通过，3：审核未通过")
    private Integer status;
}
