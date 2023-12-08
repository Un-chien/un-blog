package com.un.blog.question.service;

import com.un.blog.entities.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.question.req.QuestionREQ;
import com.un.blog.util.base.BaseRequest;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-04-28
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 按热度排序问答里列表
     * */
    Result findHotList(BaseRequest<Question> req);

    /**
     * 最新问答分页列表
     */
    Result findNewList(BaseRequest<Question> req);

    /**
     * 未完成问答分页列表
     */
    Result findWaitList(BaseRequest<Question> req);

    /**
     * 根据标签id查询问答列表
     */
    Result findListByLabelId(BaseRequest<Question> req, String labelId);

    /**
     * 问根据问答id查询问答详情及label列表
     * 查询问答所属的标签 ids
     * 通过openFeign远程调用Article微服务查询
     * 查询问题对应的回答列表
     */
    Result findById(String id);

    /**
     * 更新浏览次数,如果用户点击了文章，则浏览次数+1
     */
    Result updateViewCount(String id);

    /**
     * 问题新增与更新
     * */
    Result updateOrSave(Question question);

    /**
     * 修改问答状态
     */
    Result updateStatus(String id, Integer status);

    /**
     * 删除问答
     */
    Result deleteById(String id);

    /**
     * 更新点赞数
     */
    Result updateThumhup(String id, int count);

    /**
     * 根据用户查询该用户的所有问答列表
     */
    Result findListByUserId(QuestionREQ req);

    /**
     * 获取问答总数
     */
    Result getQuestionTotal();



}
