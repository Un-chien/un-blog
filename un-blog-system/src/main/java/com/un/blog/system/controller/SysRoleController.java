package com.un.blog.system.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.un.blog.entities.SysRole;
import com.un.blog.system.req.SysRoleREQ;
import com.un.blog.system.service.ISysRoleService;
import com.un.blog.util.base.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
@Api(value = "角色管理接口",description = "角色管理接口，提供增删改查功能")
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("根据角色名查询角色列表接口")
    @PostMapping("/search")
    public Result search(@RequestBody SysRoleREQ req) {
        return sysRoleService.queryPage(req);
    }

    @ApiOperation("新增角色接口")
    @PostMapping
    public Result save(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @ApiOperation("根据id查询角色")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok(sysRoleService.getById(id));
    }

    @ApiOperation("根据id更新角色信息接口")
    @PutMapping
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setUpdateDate(new Date());
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }


    @ApiOperation("根据id删除角色及关联接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return sysRoleService.deleteById(id);
    }

    @ApiOperation("根据角色id查询对应菜单id列表接口")
    @GetMapping("/{id}/menu/ids")
    public Result findMenuIdsById(@PathVariable("id") String id) {
        return sysRoleService.findMenuIdsById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "角色ID",required = true),
            @ApiImplicitParam(allowMultiple = true,dataType = "string",name = "menuIds",value = "菜单id集合",required = true)
    })
    @ApiOperation("新增角色菜单关系接口")
    @PostMapping("/{id}/menu/save")
    public Result saveRoleMenu(@PathVariable("id") String id, @RequestBody List<String> menuIds) {
        return sysRoleService.saveRoleMenu(id, menuIds);
    }

}
