package com.step.template.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.step.template.main.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<String> selectByUserId(@Param("userId") Integer userId);
}
