package com.un.blog.system.mapper;

import com.un.blog.entities.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据角色id删除角色菜单关系表
     * */
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色id查询该角色拥有的菜单id列表
     * */
    List<String> findMenuIdsById(@Param("id") String id);


    /**
     * 新增角色菜单权限数据到中间表sys_role_menu
     * */
    boolean saveRoleMenu(@Param("roleId") String roleId, @PathVariable("menuIds") List<String> menuIds);
}
