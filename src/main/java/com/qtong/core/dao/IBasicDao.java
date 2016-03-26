package com.qtong.core.dao;


import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;

import java.util.List;

/**
 * Created by ZML on 2016/1/15.
 */
public interface IBasicDao extends IBaseDao {

    User getUserByPrincipal(String principal);

    List<Permission> listAllPermissions();

    Role getRoleByRoleName(String roleName);
}
