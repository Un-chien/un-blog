package com.un.blog.question.req;


import com.un.blog.entities.Question;
import com.un.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户相关通用查询对象")
public class QuestionREQ extends BaseRequest<Question> {

    @ApiModelProperty(value = "用户id")
    private String userId;
}
