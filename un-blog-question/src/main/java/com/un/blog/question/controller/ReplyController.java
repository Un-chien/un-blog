package com.un.blog.question.controller;


import com.un.blog.question.service.IReplyService;
import com.un.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 回答信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
@Api(value = "问答管理API接口")
@RestController
@RequestMapping("/reply")
public class ReplyController {


    @Autowired
    private IReplyService replyService;

    @ApiOperation("根据id删除评论")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return replyService.deleteById(id);
    }
}
