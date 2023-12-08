package com.un.blog.feign;

import com.un.blog.entities.Label;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// value指定目标微服务名字，path目标微服务上下文路径的值
@FeignClient(value = "article-server",path = "/article")
public interface IFeignArticleController {

    @ApiImplicitParam(allowMultiple = true,dataType = "String",
            name = "labelIds",value = "标签ID集合",required = true)
    @ApiOperation("feign接口-根据标签ids查询对应标签信息")
    @PostMapping("/api/feign/label/list/{ids}")
    List<Label> getLabelListByIds(@PathVariable("ids") List<String> labelIds);
}
