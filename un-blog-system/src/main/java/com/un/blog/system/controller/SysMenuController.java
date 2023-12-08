package com.un.blog.system.controller;


import com.un.blog.entities.SysMenu;
import com.un.blog.system.req.SysMenuREQ;
import com.un.blog.system.service.ISysMenuService;
import com.un.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Api(value = "菜单管理接口", description = "菜单管理，提供增删改查等功能")
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("根据菜单名称查询接口列表")
    @PostMapping("/search")
    public Result search(@RequestBody SysMenuREQ req) {
        return sysMenuService.queryList(req);
    }

    @ApiOperation("根据菜单id删除菜单及子菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true)
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return sysMenuService.deleteById(id);
    }

    @ApiOperation("新增菜单信息接口")
    @PostMapping
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("查询菜单接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok(sysMenuService.getById(id));
    }

    @ApiOperation("更新菜单接口")
    @PutMapping
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateDate(new Date());
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }
}
