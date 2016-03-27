package com.qtong.core.dao.impl;


import com.qtong.core.dao.IBasicDao;
import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZML on 2016/1/15.
 */
@Repository
public class BasicDaoImpl extends BaseDao implements IBasicDao {

    @Override
    public User getUserByPrincipal(String principal) {

        User user = (User) getSession().createCriteria(User.class).
                add(Restrictions.disjunction().add(Restrictions.eq("username", principal))
                        .add(Restrictions.eq("phone", principal))
                        .add(Restrictions.eq("email", principal)))
                .uniqueResult();
        return user;
    }

    @Override
    public List<Permission> listAllPermissions() {
        return getSession().createCriteria(Permission.class).list();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return (Role) getSession().createCriteria(Role.class).add(Restrictions.eq("roleName", roleName)).uniqueResult();
    }

    @Override
    public List<Role> getHasPermissionRoles(Permission permission) {

        String hql = "select r from Role r inner join r.permissions as perm where perm.permId =:permission";

        Session session = getSession();


        List result = session.createQuery(hql).setString("permission", permission.getPermId()).list();


        return result;
    }

}
