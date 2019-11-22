package com.step.template.main.service;

import com.step.template.main.entity.User;
import com.step.template.main.exception.MyException;
import com.step.template.main.util.BCrypt;
import com.step.template.main.util.Constant;
import com.step.template.main.util.JwtUtil;
import com.step.template.main.util.ScopeUtil;
import com.step.template.main.vo.MyScope;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
@Transactional
public class AccountService {
    private final UserService userService;
    private final PermissionService permissionService;
    private final CacheManager cacheManager;

    /**
     * 登录
     */
    public Map<String, Object> login(String phone, String password) {
        User user = userService.selectByPhone(phone);
        checkUser(user);
        //验证密码
        if (BCrypt.checkpw(password, user.getPassword())) {
            MyScope myScope = cacheScope(user.getId());
            Map<String, Object> result = new HashMap<>();
            result.put("userId", myScope.getUserId());
            result.put("username", myScope.getUsername());
            result.put("phone", myScope.getPhone());
            result.put("name", myScope.getName());
            result.put("token", JwtUtil.createToken(user.getId()));
            result.put("permissions", myScope.getPermissions());
            //清除缓存
            Cache myScopeCache = cacheManager.getCache(Constant.SCOPE);
            if (myScopeCache != null) {
                myScopeCache.evict(user.getId());
            }
            return result;
        } else {
            throw new MyException("密码错误");
        }
    }

    /**
     * 修改密码
     */
    public void changePassword(String oldPassword, String newPassword) {
        User user = userService.getById(ScopeUtil.getUserId());
        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            userService.updatePassword(user.getId(), newPassword);
        } else {
            throw new MyException("原密码错误");
        }
    }

    /**
     * 初始化MyScope，并放入缓存中
     */
    @Cacheable(Constant.SCOPE)
    public MyScope cacheScope(Integer userId) {
        User user = userService.getById(userId);
        Set<String> permissions = permissionService.selectByUseId(user.getId());
        MyScope scope = new MyScope();
        scope.setName(user.getName());
        scope.setUsername(user.getUsername());
        scope.setUserId(user.getId());
        scope.setPhone(user.getPhone());
        scope.setPermissions(permissions);
        return scope;
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new MyException("用户不存在");
        }
        if (Constant.UserStatus.disabled.name().equals(user.getStatus())) {
            throw new MyException("用户已被禁用");
        }
    }
}
