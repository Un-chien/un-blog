package com.un.blog.article.req;

import com.un.blog.entities.Label;
import com.un.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "二级菜单Lable通用查询对象",description = "二级菜单查询条件")
@Data
public class LableREQ extends BaseRequest<Label> {

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "标签所属分类ID")
    private String categoryId;
}
