package com.qtong.health.basic.service;

import com.qtong.health.basic.model.Permission;
import com.qtong.health.basic.model.Role;
import com.qtong.health.basic.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
public interface BasicService {

    void createUser(User user);

    void saveOrUpdateBean(Object object);

    Object getBeanById(Class clazz, Serializable id);

    Set<String> getUserRoleNames(String username);

    Set<String> getUserPermissions(String username);

    User queryUniqueUser(String username);

    List<Permission> listAllPermissions();

    Role getRoleByRoleName(String roleName);

    List<Role> getHasPermissionRoles(Permission permission);


}
