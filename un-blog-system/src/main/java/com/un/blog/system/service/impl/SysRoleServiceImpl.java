package com.un.blog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.un.blog.entities.SysRole;
import com.un.blog.system.mapper.SysRoleMapper;
import com.un.blog.system.req.SysRoleREQ;
import com.un.blog.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.un.blog.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public Result queryPage(SysRoleREQ req) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.eq("name", req.getName());
        }
        IPage<SysRole> iPage = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(iPage);
    }

    @Transactional
    @Override
    public Result deleteById(String id) {
        //1. 删除角色表中的信息
        baseMapper.deleteById(id);
        //2. 删除中间表信息
        baseMapper.deleteRoleMenuByRoleId(id);
        return Result.ok();
    }

    @Override
    public Result findMenuIdsById(String id) {

        return Result.ok(baseMapper.findMenuIdsById(id));
    }

    @Transactional
    @Override
    public Result saveRoleMenu(String roleId, List<String> menuIds) {
        //1. 先删除角色菜单表数据
        baseMapper.deleteRoleMenuByRoleId(roleId);
        //2. 保存到表中
        if (CollectionUtils.isNotEmpty(menuIds)) {
            baseMapper.saveRoleMenu(roleId, menuIds);
        }
        return Result.ok();
    }

}
