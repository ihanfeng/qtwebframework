package com.qtong.core.service;

import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.model.UserInfoInheritDemo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZML on 2016/3/24.
 */
public class BasicServiceTest {

    ApplicationContext context;
    BasicService basicService = null;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        basicService = context.getBean(BasicService.class);
    }

    @Test
    public void test() {
        BasicService basicService = context.getBean(BasicService.class);

        initPermissions();

        initRoles();

        createAdministrator();

        Role role = basicService.getRoleByRoleName("system");

        role.getMenus().size();

        for (Permission permission : role.getPermissions()) {
            System.out.println(permission.getName());
            List<Role> roles = basicService.getHasPermissionRoles(permission);


            for (Role system : roles) {
                System.out.println(permission.getName());
                for (Permission permission1 : system.getPermissions()) {
                    System.out.println(permission.getName());
                    // List<Role> roles= basicService.getHasPermissionRoles(permission);
                }

            }
        }


    }

    @Test
    public void getUserInfo(){
        BasicService basicService = context.getBean(BasicService.class);

        User user=basicService.queryUniqueUser("admin");

//         UserInfoInheritDemo demo=new UserInfoInheritDemo();
//
//        demo.setAge(11);
//
//        demo.setNickName("管理员");
//
//        user.setUserInfo(demo);
//
//        basicService.saveOrUpdateBean(demo);
//
//        basicService.saveOrUpdateBean(user);
//
//        user=basicService.queryUniqueUser("admin");

        System.out.println(user.getUserInfo().toString());
    }


    private void createAdministrator() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<Role> roles = new HashSet<>();
        Role system = basicService.getRoleByRoleName("system");
        roles.add(system);
        admin.setRoles(roles);
        basicService.createUser(admin);
    }

    private void initRoles() {
        Role system = new Role();
        system.setRoleName("system");
        system.setPermissions(basicService.listAllPermissions());
        basicService.saveOrUpdateBean(system);
    }

    private void initPermissions() {
        Permission system = new Permission();
        system.setExpression("system");
        system.setName("system");
        basicService.saveOrUpdateBean(system);
    }
}
