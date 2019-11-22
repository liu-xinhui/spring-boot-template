package com.step.template.main.controller;

import com.step.template.main.service.PermissionService;
import com.step.template.main.vo.TreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = "权限")
@AllArgsConstructor
@RestController
@RequestMapping("permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @ApiOperation("获取列表")
    @GetMapping
    public List<TreeNode> selectAll() {
        return permissionService.selectAll();
    }

    @ApiOperation("获取某个用户的权限列表")
    @GetMapping("userId/{userId}")
    public Set<String> selectByUserId(@PathVariable Integer userId) {
        return permissionService.selectByUseId(userId);
    }
}
