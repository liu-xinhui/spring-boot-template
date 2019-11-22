package com.step.template.main.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.step.template.main.config.annotation.Auth;
import com.step.template.main.entity.User;
import com.step.template.main.exception.MyException;
import com.step.template.main.service.UserService;
import com.step.template.main.svo.UserSvo;
import com.step.template.main.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.step.template.main.util.Constant.ADMIN_ID;

@Api(tags = "用户")
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Auth("user:query")
    @ApiOperation("获取列表")
    @GetMapping
    public Page<UserVo> findWithPage(UserSvo svo) {
        return userService.selectWithPage(svo);
    }

    @Auth("user:query")
    @ApiOperation("获取详情")
    @GetMapping("{id}")
    public UserVo selectByPrimaryKey(@PathVariable Integer id) {
        return userService.getVoById(id);
    }

    @Auth("user:edit")
    @ApiOperation("创建")
    @PostMapping
    public void insert(@RequestBody User user) {
        userService.insertUser(user);
    }

    @Auth("user:edit")
    @ApiOperation("编辑")
    @PutMapping
    public void updateByPrimaryKey(@RequestBody User user) {
        checkIsAdmin(user.getId());
        userService.updateUser(user);
    }

    @Auth("user:edit")
    @ApiOperation("禁用启用账号")
    @PutMapping("{id}/toggleStatus")
    public void toggleStatus(@PathVariable Integer id) {
        checkIsAdmin(id);
        userService.toggleStatus(id);
    }

    @Auth("user:edit")
    @ApiOperation("重置密码为template123456")
    @PutMapping("{id}/resetPassword")
    public void resetPassword(@PathVariable Integer id) {
        userService.updatePassword(id, "template123456");
    }

    @Auth("user:edit")
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        checkIsAdmin(id);
        userService.removeById(id);
    }

    private void checkIsAdmin(Integer id) {
        if (ADMIN_ID.equals(id)) {
            throw new MyException("不允许修改超级管理员");
        }
    }
}
