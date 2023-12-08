package com.un.blog.system.controller;


import com.un.blog.system.req.SysUserREQ;
import com.un.blog.system.service.ISysUserService;
import com.un.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Api(value = "用户管理接口",description = "用户管理接口，提供用户操作功能")
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;


    @ApiOperation("根据用户名和手机号查询用户列表")
    @PostMapping("/search")
    public Result search(@RequestBody SysUserREQ req) {
        return sysUserService.queryPage(req);
    }

    @ApiOperation("根据用户id查询对应角色ids")
    @GetMapping("/{id}/role/ids")
    public Result findRoleIdsById(@PathVariable("id") String id) {
        return sysUserService.findRoleIdsById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",required = true),
            @ApiImplicitParam(allowMultiple = true,dataType = "string",name = "roleIds",value = "角色id集合",required = true)
    })
    @ApiOperation("新增用户角色接口")
    @PostMapping("/{id}/role/save")
    public Result saveUserRole(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        return sysUserService.saveUserRole(id, roleIds);
    }

    @ApiOperation("根据用户id删除用户")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return sysUserService.deleteById(id);
    }

}
