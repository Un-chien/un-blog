package com.un.blog.article.req;

import com.un.blog.entities.Article;
import com.un.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询用户问斩共同用对象")
public class ArticleUserREQ extends BaseRequest<Article> {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "是否公开")
    private String isPublic;

}
