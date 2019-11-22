package com.step.template.main.service;

import com.step.template.main.base.BaseService;
import com.step.template.main.entity.Permission;
import com.step.template.main.entity.Role;
import com.step.template.main.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class RoleService extends BaseService<Role> {

    private final RoleMapper roleMapper;

    /**
     * 查询角色，同时查询包含的权限
     */
    public Role selectById(Integer id) {
        return roleMapper.getById(id);
    }

    /**
     * 插入角色，同时插入包含的权限
     */
    public void insertRole(Role role) {
        super.save(role);
        //插入权限
        List<Permission> permissions = role.getPermissions();
        if (isNotEmpty(permissions)) {
            roleMapper.insertRolePermission(role.getId(), permissions);
        }
    }

    /**
     * 更新角色，同时更新包含的权限
     */
    public void updateRole(Role role) {
        super.updateById(role);
        //先删除旧的权限
        roleMapper.deleteRolePermission(role.getId());
        //插入新的权限
        if (isNotEmpty(role.getPermissions())) {
            roleMapper.insertRolePermission(role.getId(), role.getPermissions());
        }
    }

    /**
     * 删除权限角色
     */
    public void deleteRole(Integer roleId) {
        super.removeById(roleId);
        //删除权限
        roleMapper.deleteRolePermission(roleId);
    }
}
