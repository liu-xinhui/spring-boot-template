package com.step.template.main.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 当前用户
 */
@Getter
@Setter
public class MyScope {
    private Integer userId;
    private String username;
    private String phone;
    private String name;
    private Integer tenantId;
    private Set<String> permissions;
}
