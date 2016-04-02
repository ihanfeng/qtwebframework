package com.qtong.health.basic.service.impl;

import com.google.common.base.Preconditions;
import com.qtong.health.basic.dao.IBasicDao;
import com.qtong.health.basic.model.Permission;
import com.qtong.health.basic.model.Role;
import com.qtong.health.basic.model.User;
import com.qtong.health.basic.service.BasicService;
import com.qtong.health.basic.utils.EndecryptUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
@Service
public class BasicServiceImpl implements BasicService {

    private IBasicDao basicDao;

    @Resource
    public void setBasicDao(IBasicDao basicDao) {
        this.basicDao = basicDao;
    }

    @Override
    public void createUser(User user) {

        Preconditions.checkNotNull(user);//判读用户是否为空，如果为空则抛出异常
        user.setActived(true);//激活当前用户
        user.setEnabled(true);//当前用户能登录
        user.setExpired(false);//设置其未过期
        //user.setLocked(false);//设置其状态为未锁定
        EndecryptUtils.encryptPassword(user);//为此用户设置密码加密
        basicDao.save(user);
    }

    @Override
    public void saveOrUpdateBean(Object object) {
        basicDao.saveOrUpdate(object);
    }

    @Override
    public Object getBeanById(Class clazz, Serializable id) {
        return basicDao.get(clazz, id);
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

        User user = this.queryUniqueUser(username);

        if (user == null) {
            return null;
        }
        Set<String> permissionExpressions = new HashSet<>();

        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                permissionExpressions.add(permission.getExpression());
            }
        }
        return permissionExpressions;
    }

    @Override
    public User queryUniqueUser(String username) {
        return basicDao.getUserByPrincipal(username);
    }

    @Override
    public List<Permission> listAllPermissions() {
        return basicDao.listAllPermissions();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return basicDao.getRoleByRoleName(roleName);
    }

    @Override
    public List<Role> getHasPermissionRoles(Permission permission) {
        return basicDao.getHasPermissionRoles(permission);
    }
}
