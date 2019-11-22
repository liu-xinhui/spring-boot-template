package com.step.template.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "未认证或者认证过期，请重新登录");
    }
}
