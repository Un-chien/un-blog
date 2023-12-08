package com.un.blog.system.service;

import com.un.blog.entities.SysMenu;
import com.un.blog.entities.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.un.blog.system.req.SysRoleREQ;
import com.un.blog.util.base.Result;

import javax.print.DocFlavor;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据角色名称查询角色
     * */
    Result queryPage(SysRoleREQ req);

    /**
     * 根据角色id删除角色菜单关系表
     */
    Result deleteById(String id);

    /**
     * 根据角色id查询该角色拥有的菜单id列表
     * */
    Result findMenuIdsById(String id);


    /**
     * 新增角色菜单权限数据到中间表sys_role_menu
     */

    Result saveRoleMenu(String roleId, List<String> menuIds);

}
