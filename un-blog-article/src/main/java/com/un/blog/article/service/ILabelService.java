package com.un.blog.article.service;

import com.un.blog.article.req.LableREQ;
import com.un.blog.entities.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */
public interface ILabelService extends IService<Label> {

    Result queryPage(LableREQ req);



}
