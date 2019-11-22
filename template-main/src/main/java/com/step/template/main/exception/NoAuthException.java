package com.step.template.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoAuthException extends ResponseStatusException {
    public NoAuthException() {
        super(HttpStatus.FORBIDDEN, "无权限");
    }
}
