package com.step.template.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.step.template.main.entity.Role;
import com.step.template.main.entity.User;
import com.step.template.main.svo.UserSvo;
import com.step.template.main.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    Page<UserVo> selectWithPage(Page<UserVo> page, @Param("svo") UserSvo svo);

    UserVo getVoById(@Param("id") Integer id);

    void insertUserRole(@Param("userId") Integer userId, @Param("roles") List<Role> roles);

    void deleteUserRole(@Param("userId") Integer userId);
}
