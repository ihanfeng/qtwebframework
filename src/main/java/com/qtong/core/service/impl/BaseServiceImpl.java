package com.qtong.core.service.impl;

import com.qtong.core.dao.IUserDao;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.service.BaseService;
import com.qtong.core.utils.EndecryptUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
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

        User encryptedUser = EndecryptUtils.md5Password(user.getUsername(), user.getPassword());
        user.setPassword(encryptedUser.getPassword());
        user.setSalt(encryptedUser.getSalt());
        userDao.createUser(user);
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public Set<String> getUserRoleNames(String username) {

        User user = this.queryUniqueUser(username);

        if(user ==null){
            return null;
        }
        Set<String> roleNames=new HashSet<>();

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
}
