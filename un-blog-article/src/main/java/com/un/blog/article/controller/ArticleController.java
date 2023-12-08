package com.un.blog.article.controller;


import com.un.blog.article.req.ArticleREQ;
import com.un.blog.article.req.ArticleUserREQ;
import com.un.blog.article.service.IArticleService;
import com.un.blog.entities.Article;
import com.un.blog.util.base.Result;
import com.un.blog.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */

@Api(value = "文章接口管理", description = "文章接口管理，提供文章增删改查")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("根据文章标题和状态查询文章列表")
    @PostMapping("/search")
    public Result search(@RequestBody ArticleREQ req) {
        return articleService.queryPage(req);
    }


    @ApiOperation("修改文章信息接口")
    @PutMapping
    public Result update(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiOperation("新增文章信息接口")
    @PostMapping
    public Result save(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiOperation("审核不通过接口")
    @GetMapping("/audit/fail/{id}")
    public Result fail(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.FAIL.getCode());
    }

    @ApiOperation("审核通过接口")
    @GetMapping("/audit/success/{id}")
    public Result success(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.SUCCESS.getCode());
    }

    @ApiOperation("删除文章接口")
    @DeleteMapping("/audit/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.DELETED.getCode());
    }

    @ApiOperation("根据用户id查询公开或非公开的文章")
    @PostMapping("/user")
    public Result findListByUserId(@RequestBody ArticleUserREQ req) {
        return articleService.findListByUserId(req);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id",required = true),
            @ApiImplicitParam(name = "count",value = "点赞数",required = true)
    })
    @ApiOperation("更新点赞数")
    @PutMapping("thumb/{id}/{count}")
    public Result updateThumhup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return articleService.updateThumhup(id, count);
    }

    @ApiOperation("统计已审核文章总记录数")
    @GetMapping("/total")
    public Result getArticleTotal(){
        return articleService.getArticleTotal();
    }

    @ApiOperation("统计各分类下的文章数")
    @GetMapping("/category/total")
    public Result categoryTotal(){
        return articleService.selectCategoryTotal();
    }
}
