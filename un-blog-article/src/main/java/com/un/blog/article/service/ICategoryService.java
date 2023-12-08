package com.un.blog.article.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.article.req.CategoryREQ;
import com.un.blog.entities.Category;
import com.un.blog.util.base.Result;

public interface ICategoryService extends IService<Category> {

    Result queryPage(CategoryREQ req);

    Result findAllNormal();

    Result findCategoryAndLabel();
}
