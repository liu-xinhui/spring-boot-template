package com.step.template.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.step.template.main.base.BaseService;
import com.step.template.main.entity.User;
import com.step.template.main.mapper.UserMapper;
import com.step.template.main.svo.UserSvo;
import com.step.template.main.util.BCrypt;
import com.step.template.main.util.Constant;
import com.step.template.main.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class UserService extends BaseService<User> {

    private final UserMapper userMapper;

    public Page<UserVo> selectWithPage(UserSvo svo) {
        return userMapper.selectWithPage(svo.getPage(), svo);
    }

    /**
     * 新增用户,同时插入角色
     */
    public void insertUser(User user) {
        String phone = user.getPhone();
        checkUnique(User::getPhone, phone, null, "手机号已经存在,请更换");
        //随机生成12位字母作为用户名
        user.setUsername(RandomStringUtils.randomAlphabetic(12).toLowerCase());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setStatus(Constant.UserStatus.enabled.name());
        super.save(user);
        //插入角色
        if (isNotEmpty(user.getRoles())) {
            userMapper.insertUserRole(user.getId(), user.getRoles());
        }
    }

    /**
     * 更新用户,不允许更新用户名，密码
     */
    public void updateUser(User user) {
        checkUnique(User::getPhone, user.getPhone(), user.getId(), "手机号已经存在,请更换");
        //不更新字段设置为null
        user.setUsername(null);
        user.setPassword(null);
        user.setStatus(null);
        super.updateById(user);
        //先删除旧的权限
        userMapper.deleteUserRole(user.getId());
        //插入新的权限
        if (isNotEmpty(user.getRoles())) {
            userMapper.insertUserRole(user.getId(), user.getRoles());
        }
    }

    public void toggleStatus(int userId) {
        User userOld = super.getById(userId);
        String status = Constant.UserStatus.enabled.name().equals(userOld.getStatus()) ? Constant.UserStatus.disabled.name() : Constant.UserStatus.enabled.name();
        userOld.setStatus(status);
        super.updateById(userOld);
    }

    /**
     * 根据id查询
     */
    public UserVo getVoById(Integer id) {
        return userMapper.getVoById(id);
    }

    /**
     * 根据phone获取用户
     */
    public User selectByPhone(String phone) {
        return super.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }

    /**
     * 设置某个用户的密码
     */
    public void updatePassword(Integer id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        super.updateById(user);
    }
}
