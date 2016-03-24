package com.qtong.core.service;

import com.qtong.core.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZML on 2016/3/24.
 */
public class BaseServiceTest {

    ApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test() {
        BaseService baseService = context.getBean(BaseService.class);

        User user = new User();

        user.setUsername("test1");

        user.setPassword("test1");

        baseService.createUser(user);

        Assert.assertNotNull("userid 为空，创建失败", user.getUserId());
    }


}
