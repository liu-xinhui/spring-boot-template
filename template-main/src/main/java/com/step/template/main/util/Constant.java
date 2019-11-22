package com.step.template.main.util;

import lombok.Getter;

public class Constant {
    /**
     * 超级管理员id，会对此id忽略权限校验
     */
    public static final Integer ADMIN_ID = 1;

    /**
     * 认证请求头名称
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * 当前用户信息Attribute name
     */
    public static final String SCOPE = "myScope";

    /**
     * 用户状态
     */
    public enum UserStatus {
        disabled("禁用"), enabled("启用");
        @Getter
        private String desc;

        UserStatus(String desc) {
            this.desc = desc;
        }
    }
}
