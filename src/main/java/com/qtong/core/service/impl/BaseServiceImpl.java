package com.qtong.core.service.impl;

import com.google.common.base.Preconditions;
import com.qtong.core.dao.IUserDao;
import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.service.BaseService;
import com.qtong.core.utils.EndecryptUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
@Service
public class BaseServiceImpl implements BaseService {

    private IUserDao userDao;

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {

        Preconditions.checkNotNull(user);//判读用户是否为空，如果为空则抛出异常
        user.setActived(true);//激活当前用户
        user.setEnable(true);//当前用户能登录
        user.setExpired(false);//设置其未过期
        //user.setLocked(false);//设置其状态为未锁定
        EndecryptUtils.encryptPassword(user);//为此用户设置密码加密
        userDao.save(user);
    }

    @Override
    public void create(Object object) {
        userDao.save(object);
    }

    @Override
    public Set<String> getUserRoleNames(String username) {

        User user = this.queryUniqueUser(username);

        if (user == null) {
            return null;
        }
        Set<String> roleNames = new HashSet<>();

        for (Role role : user.getRoles()) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }

    @Override
    public Set<String> getUserPermissions(String username) {
        return null;
    }

    @Override
    public User queryUniqueUser(String username) {
        return userDao.getUserByPrincipal(username);
    }

    @Override
    public List<Permission> listAllPermissions() {
        return userDao.listAllPermissions();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return userDao.getRoleByRoleName(roleName);
    }
}
