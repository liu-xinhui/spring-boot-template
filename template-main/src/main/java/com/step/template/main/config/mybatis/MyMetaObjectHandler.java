package com.step.template.main.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.step.template.main.util.ScopeUtil;
import com.step.template.main.vo.MyScope;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充器
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        try {
            MyScope scope = ScopeUtil.getScope();
            this.setFieldValByName("createBy", scope.getName(), metaObject);
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateBy", scope.getName(), metaObject);
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        try {
            MyScope scope = ScopeUtil.getScope();
            this.setFieldValByName("updateBy", scope.getName(), metaObject);
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
