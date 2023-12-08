package com.un.blog.article.feign;

import com.un.blog.article.service.ILabelService;
import com.un.blog.entities.Label;
import com.un.blog.feign.IFeignArticleController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "被远程调用的文章微服务接口")
@RestController
public class FeignArticleController implements IFeignArticleController {

    @Autowired
    private ILabelService labelService;

    @Override
    public List<Label> getLabelListByIds(List<String> labelIds) {
        return labelService.listByIds(labelIds);
    }
}
