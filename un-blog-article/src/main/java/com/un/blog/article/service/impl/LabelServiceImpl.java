package com.un.blog.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.un.blog.article.req.LableREQ;
import com.un.blog.entities.Label;
import com.un.blog.article.mapper.LabelMapper;
import com.un.blog.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.un.blog.util.base.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-04-15
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Override
    public Result queryPage(LableREQ req) {

        IPage<Label> page = baseMapper.queryPage(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public boolean updateById(Label label) {
        label.setUpdateDate(new Date());
        return super.updateById(label);
    }

}
