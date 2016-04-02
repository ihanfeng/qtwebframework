package com.qtong.health.basic.dao;


import com.qtong.health.basic.model.Permission;
import com.qtong.health.basic.model.Role;
import com.qtong.health.basic.model.User;

import java.util.List;

/**
 * Created by ZML on 2016/1/15.
 */
public interface IBasicDao extends IBaseDao {

    User getUserByPrincipal(String principal);

    List<Permission> listAllPermissions();

    Role getRoleByRoleName(String roleName);

    List<Role> getHasPermissionRoles(Permission permission);
}
