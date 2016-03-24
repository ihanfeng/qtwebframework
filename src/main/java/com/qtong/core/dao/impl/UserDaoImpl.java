package com.qtong.core.dao.impl;


import com.qtong.core.dao.IUserDao;
import com.qtong.core.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by ZML on 2016/1/15.
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    public void createUser(User user) {
        super.save(user);
    }

    @Override
    public User getUserByPrincipal(String principal) {

        User user = (User) getSession().createCriteria(User.class).
                add(Restrictions.disjunction().add(Restrictions.eq("username", principal))
                        .add(Restrictions.eq("phone", principal))
                        .add(Restrictions.eq("email", principal)))
                .uniqueResult();
        return user;


    }
}
