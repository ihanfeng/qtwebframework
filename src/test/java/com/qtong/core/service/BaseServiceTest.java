package com.qtong.core.service;

import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
public class BaseServiceTest {

    ApplicationContext context;
    BaseService baseService = null;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        baseService = context.getBean(BaseService.class);
    }

    @Test
    public void test() {
        BaseService baseService = context.getBean(BaseService.class);
//
//        User user = new User();
//
//        user.setUsername("test1");
//
//        user.setPassword("test1");
//
//        baseService.createUser(user);

/*        initPermissions();
        initRoles();*/
        createAdministrator();

        //      Assert.assertNotNull("userid 为空，创建失败", user.getUserId());
    }


    private void createAdministrator() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<Role> roles = new HashSet<>();
        Role system = baseService.getRoleByRoleName("system");
        roles.add(system);
        admin.setRoles(roles);
        baseService.createUser(admin);
    }

    private void initRoles() {
        Role system = new Role();
        system.setRoleName("system");
        system.setPermissions(baseService.listAllPermissions());
        baseService.create(system);
    }

    private void initPermissions() {
        Permission system = new Permission();
        system.setExpression("system");
        system.setName("system");
        baseService.create(system);
    }
}
