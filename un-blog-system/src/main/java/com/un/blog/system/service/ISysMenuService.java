package com.un.blog.system.service;

import com.un.blog.entities.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.system.req.SysMenuREQ;
import com.un.blog.util.base.Result;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据菜单名称查询菜单列表
     * */
    Result queryList(SysMenuREQ req);

    /**
     * 根据菜单id删除
     */
    Result deleteById(String id);

}
