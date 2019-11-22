package com.step.template.main.controller;

import com.step.template.main.config.annotation.NoAuth;
import com.step.template.main.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "认证")
@AllArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @NoAuth
    @ApiOperation("登录")
    @GetMapping("login")
    public Map<String, Object> login(@RequestParam @ApiParam(value = "账号", required = true) String phone,
                                     @RequestParam @ApiParam(value = "密码", required = true) String password) {
        return accountService.login(phone, password);
    }

    @ApiOperation("修改密码")
    @GetMapping("changePassword")
    public void changePassword(@RequestParam @ApiParam(value = "原密码", required = true) String oldPassword,
                               @RequestParam @ApiParam(value = "新密码", required = true) String newPassword) {
        accountService.changePassword(oldPassword, newPassword);
    }
}
