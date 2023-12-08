package com.un.blog.system.mapper;

import com.un.blog.entities.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author un
 * @since 2023-05-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> findRoleIdsById(@Param("id") String id);

    Boolean deleteUserRoleByUserId(@Param("userId") String userId);

    Boolean saveUserRole(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}
