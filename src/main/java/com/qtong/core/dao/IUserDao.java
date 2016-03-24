package com.qtong.core.dao;


import com.qtong.core.model.User;

/**
 * Created by ZML on 2016/1/15.
 */
public interface IUserDao extends IBaseDao {

    void createUser(User user);

    User getUserByPrincipal(String principal);
}
