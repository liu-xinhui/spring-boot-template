package com.step.template.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.step.template.main.entity.Permission;
import com.step.template.main.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Role getById(@Param("id") Integer id);

    void insertRolePermission(@Param("roleId") Integer roleId, @Param("permissions") List<Permission> permissions);

    void deleteRolePermission(@Param("roleId") Integer roleId);
}
