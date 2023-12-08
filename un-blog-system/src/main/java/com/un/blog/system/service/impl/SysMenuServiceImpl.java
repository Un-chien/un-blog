package com.un.blog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.un.blog.entities.SysMenu;
import com.un.blog.system.mapper.SysMenuMapper;
import com.un.blog.system.req.SysMenuREQ;
import com.un.blog.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.un.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public Result queryList(SysMenuREQ req) {
        //1. 根据条件查询出所有菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }
        wrapper.orderByAsc("sort").orderByDesc("update_date");
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        //2. 将菜单封装为树形接口
        List<SysMenu> sysMenus = buildTree(menuList);
        return Result.ok(sysMenus);
    }

    @Transactional
    @Override
    public Result deleteById(String id) {
        //1. 删除当前id的菜单
        baseMapper.deleteById(id);
        //2. 删除子菜单
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        baseMapper.delete(wrapper);
        return Result.ok();
    }

    /**
     * 将菜单封装为树结构
     * */
    private List<SysMenu> buildTree(List<SysMenu> menuList) {
        //1. 获取根菜单
        List<SysMenu> rootMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId().equals("0")) {
                rootMenuList.add(menu);
            }
        }
        //2. 根菜单下的子菜单
        for (SysMenu menu : rootMenuList) {
            childrenMenu(menuList, menu);
        }
        return rootMenuList;
    }

    /**
     * 判断id是否等于所有菜单中的某一个菜单的parentid，如果等于，则这个菜单就是子菜单
     * */
    private SysMenu childrenMenu(List<SysMenu> menuList, SysMenu menu) {
        //存放menu菜单对象的所有子菜单
        List<SysMenu> children = new ArrayList<>();
        //循环所有菜单，第一次循环判断是否为跟的子菜单
        for (SysMenu m : menuList) {
//            判断id是否等于所有菜单中的某一个菜单的parentid
            if (m.getParentId().equals(menu.getId())) {
                //递归查询
                children.add(childrenMenu(menuList, m));
            }
        }
        menu.setChildren(children);
        return menu;
    }
}
