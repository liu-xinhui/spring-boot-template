package com.step.template.main.service;


import com.step.template.main.base.BaseService;
import com.step.template.main.entity.Permission;
import com.step.template.main.mapper.PermissionMapper;
import com.step.template.main.util.CommonUtil;
import com.step.template.main.vo.TreeNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionService extends BaseService<Permission> {
    private final PermissionMapper permissionMapper;

    public List<TreeNode> selectAll() {
        List<Permission> permissions = super.list();
        //转化为TreeNodeList
        List<TreeNode> treeNodes = permissions.stream().map(item -> new TreeNode(item.getId(), item.getParentId(), item.getName(), item)).collect(Collectors.toList());
        //转化为树
        return CommonUtil.listToTree(treeNodes);
    }

    public Set<String> selectByUseId(Integer userId) {
        return permissionMapper.selectByUserId(userId);
    }

}
