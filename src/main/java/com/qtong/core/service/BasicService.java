package com.qtong.core.service;

import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
public interface BasicService {

    void createUser(User user);

    void create(Object object);

    Set<String> getUserRoleNames(String username);

    Set<String> getUserPermissions(String username);

    User queryUniqueUser(String username);

    List<Permission> listAllPermissions();

    Role getRoleByRoleName(String roleName);

    List<Role> getHasPermissionRoles(Permission permission);



}
