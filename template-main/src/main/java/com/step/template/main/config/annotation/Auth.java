package com.step.template.main.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    /**
     * 权限名称，为空表示只需要登录不需要权限,多个权限为或者关系
     */
    String[] value() default {};
}
