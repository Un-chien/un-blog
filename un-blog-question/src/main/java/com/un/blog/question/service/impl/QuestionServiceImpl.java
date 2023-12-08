package com.un.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.un.blog.entities.Label;
import com.un.blog.entities.Question;
import com.un.blog.feign.IFeignArticleController;
import com.un.blog.question.req.QuestionREQ;
import com.un.blog.question.mapper.QuestionMapper;
import com.un.blog.question.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.un.blog.util.base.BaseRequest;
import com.un.blog.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Override
    public Result findHotList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
//        where status=1 or status=2   in(1,2) status!=0
        wrapper.orderByDesc("reply");
        return Result.ok(baseMapper.selectPage(req.getPage(),wrapper));
    }

    @Override
    public Result findNewList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
//        where status=1 or status=2   in(1,2) status!=0
        wrapper.orderByDesc("update_date");
        return Result.ok(baseMapper.selectPage(req.getPage(),wrapper));
    }

    @Override
    public Result findWaitList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
//        where status=1 or status=2   in(1,2) status!=0
        wrapper.eq("reply", 0);
        wrapper.orderByDesc("create_date");
        return Result.ok(baseMapper.selectPage(req.getPage(),wrapper));
    }

    @Override
    public Result findListByLabelId(BaseRequest<Question> req, String labelId) {
        if (StringUtils.isEmpty(labelId)) {
            return Result.error("标签id不能为空");
        }
        return Result.ok(baseMapper.findListByLabelId(req.getPage(), labelId));
    }

    @Autowired
    private IFeignArticleController feignArticleController;

    @Override
    public Result findById(String id) {
//      查询问答所属的标签 ids
        Question question = baseMapper.findQuestionAndLabelIdsById(id);
        if (question == null) {
            return Result.error("没有这个问答");
        }
//      通过openFeign远程调用Article微服务查询
        if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
            List<Label> labelList = feignArticleController.getLabelListByIds(question.getLabelIds());
            question.setLabelList(labelList);
        }
//      查询问题对应的回答列表
        return Result.ok(question);
    }

    @Override
    public Result updateViewCount(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }
        Question question = baseMapper.selectById(id);
        if (question == null) {
            return Result.error("问题不存在");
        }
        question.setViewCount(question.getViewCount() + 1);
        baseMapper.updateById(question);

        return Result.ok();
    }

    @Transactional
    @Override
    public Result updateOrSave(Question question) {
        //1.如果是更新，删除中间表中数据，且设置更新时间
        if (StringUtils.isNotEmpty(question.getId())) {
            baseMapper.deleteQuestionLabel(question.getId());
            question.setUpdateDate(new Date());
        }
        //2. 调用更新或删除方法
        super.saveOrUpdate(question);
        //3. 将label写入中间表
        if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
            baseMapper.saveQuestionLabel(question.getId(), question.getLabelIds());
        }
        return Result.ok(question.getId());
    }

    @Override
    public Result updateStatus(String id, Integer status) {
        //根据id查出question
        Question question = baseMapper.selectById(id);
        // 修改状态
        question.setStatus(status);
        question.setUpdateDate(new Date());
        // 更新数据库
        baseMapper.updateById(question);
        return Result.ok();
    }

    @Override
    public Result deleteById(String id) {
        return this.updateStatus(id,0);
    }

    @Override
    public Result updateThumhup(String id, int count) {
        if (count != -1 && count != 1) {
            return Result.error("无效操作");
        }
        if (StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }
        Question question = baseMapper.selectById(id);
        if (question == null) {
            return Result.error("问题不存在");
        }
        if (question.getThumhup() <= 0 && count == -1) {
            return Result.error("无效操作");
        }
        question.setThumhup(question.getThumhup() + count);
        baseMapper.updateById(question);
        return Result.ok();
    }

    @Override
    public Result findListByUserId(QuestionREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("用户信息无效");
        }
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        wrapper.eq("user_id", req.getUserId());
        wrapper.orderByDesc("update_date");

        return Result.ok(baseMapper.selectPage(req.getPage(),wrapper));
    }

    @Override
    public Result getQuestionTotal() {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        Integer count = baseMapper.selectCount(wrapper);
        return Result.ok(count);
    }

}
