package com.un.blog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.un.blog.entities.SysUser;
import com.un.blog.system.mapper.SysUserMapper;
import com.un.blog.system.req.SysUserREQ;
import com.un.blog.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.un.blog.util.base.Result;
import com.un.blog.util.enums.ResultEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Result queryPage(SysUserREQ req) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getUsername())) {
            wrapper.like("username", req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getMobile())) {
            wrapper.like("mobile", req.getMobile());
        }
        wrapper.orderByDesc("update_date");
        return Result.ok(baseMapper.selectPage(req.getPage(), wrapper));
    }

    @Override
    public Result findRoleIdsById(String id) {

        return Result.ok(baseMapper.findRoleIdsById(id));
    }

    @Transactional
    @Override
    public Result saveUserRole(String userId, List<String> roleIds) {
        baseMapper.deleteUserRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(userId, roleIds);
        }
        return Result.ok();
    }

    @Override
    public Result deleteById(String id) {
        //1.根据id查出用户
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            return Result.error(ResultEnum.USER_NOT_EXSIT.getDesc());
        }
        //2.删除
        sysUser.setIsEnabled(0);
        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }
}
