package com.qtong.core.dao.impl;


import com.qtong.core.dao.IUserDao;
import com.qtong.core.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by ZML on 2016/1/15.
 */
@Repository
public class UserDao extends BaseDao implements IUserDao {

    public void createUser(User user) {
        super.save(user);
    }
}
