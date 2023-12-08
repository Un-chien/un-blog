package com.un.blog.question.api;

import com.un.blog.entities.Question;
import com.un.blog.question.service.IQuestionService;
import com.un.blog.util.base.BaseRequest;
import com.un.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "问答管理公开接口")
@RestController
@RequestMapping("/question/api")
public class ApiQuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("分页查询热门问答接口")
    @PostMapping("/hot")
    public Result findHotList(@RequestBody BaseRequest<Question> req) {
        return questionService.findHotList(req);
    }

    @ApiOperation("分页查询最新问答接口")
    @PostMapping("/new")
    public Result findNewList(@RequestBody BaseRequest<Question> req) {
        return questionService.findNewList(req);
    }

    @ApiOperation("分页查询最新问答接口")
    @PostMapping("/wait")
    public Result findWaitList(@RequestBody BaseRequest<Question> req) {
        return questionService.findWaitList(req);
    }

    @ApiOperation("分页查询标签下的问答列表接口")
    @PostMapping("/list/{labelId}")
    public Result findListByLabelId(@RequestBody BaseRequest<Question> req, @PathVariable("labelId") String labelId) {
        return questionService.findListByLabelId(req, labelId);

    }

    @ApiOperation("查询问答详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return questionService.findById(id);
    }

    @ApiOperation("更新浏览数接口")
    @PutMapping("/viewcount/{id}")
    public Result updateViewConut(String id) {
        return questionService.updateViewCount(id);
    }
}
