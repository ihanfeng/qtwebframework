package com.qtong.core.service;

import com.qtong.core.model.User;

import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
public interface BaseService {

    void createUser(User user);

    void create(Object object);

    Set<String> getUserRoleNames(String username);

    Set<String> getUserPermissions(String username);

    User queryUniqueUser(String username);
}