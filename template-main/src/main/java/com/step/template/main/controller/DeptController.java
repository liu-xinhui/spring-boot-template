package com.step.template.main.controller;

import com.step.template.main.config.annotation.Auth;
import com.step.template.main.entity.Dept;
import com.step.template.main.service.DeptService;
import com.step.template.main.vo.TreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "部门")
@AllArgsConstructor
@RestController
@RequestMapping("depts")
public class DeptController {

    private final DeptService deptService;

    @ApiOperation("获取部门树")
    @GetMapping
    public List<TreeNode> selectAll() {
        return deptService.selectAll();
    }

    @ApiOperation("获取单个数据")
    @GetMapping("{id}")
    public Dept selectById(@PathVariable Integer id) {
        return deptService.getById(id);
    }

    @Auth("dept:edit")
    @ApiOperation("创建")
    @PostMapping
    public void insert(@RequestBody Dept dept) {
        deptService.save(dept);
    }

    @Auth("dept:edit")
    @ApiOperation("编辑")
    @PutMapping
    public void updateById(@RequestBody Dept dept) {
        deptService.updateById(dept);
    }

    @Auth("dept:edit")
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        deptService.deleteById(id);
    }
}
