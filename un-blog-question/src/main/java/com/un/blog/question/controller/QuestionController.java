package com.un.blog.question.controller;


import com.un.blog.entities.Question;
import com.un.blog.question.req.QuestionREQ;
import com.un.blog.question.service.IQuestionService;
import com.un.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
@Api(value = "问答管理接口")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("修改问答信息接口")
    @PutMapping
    public Result update(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }

    @ApiOperation("新增问答信息接口")
    @PostMapping
    public Result save(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }
    @ApiOperation("删除问答接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return questionService.deleteById(id);
    }

    @ApiOperation("更新点赞数接口")
    @PutMapping("/thumb/{id}/{count}")
    public Result updateThumhup(@PathVariable("id")String id, @PathVariable("count") int count) {
        return questionService.updateThumhup(id, count);
    }


    @ApiOperation("根据用户id查询问题列表")
    @PostMapping("/user")
    public Result findListByUserId(@RequestBody QuestionREQ req) {
        return questionService.findListByUserId(req);
    }

    @ApiOperation("查询提问总数记录")
    @GetMapping("/total")
    public Result questionTotal(){
        return questionService.getQuestionTotal();
    }

}
