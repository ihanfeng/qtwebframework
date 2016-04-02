package com.qtong.health.basic.web.controller;

import com.qtong.health.basic.model.Role;
import com.qtong.health.basic.model.User;
import com.qtong.health.basic.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZML on 2016/3/26.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BasicService basicService;


    @RequestMapping("/hello")

    public
    @ResponseBody
    User sayHello() {

        User admin = new User();
        String username="hello" + System.currentTimeMillis();
        admin.setUsername(username);
        admin.setPassword("hello");
        Set<Role> roles = new HashSet<>();
        Role system = basicService.getRoleByRoleName("system");
        roles.add(system);
        admin.setRoles(roles);
        basicService.createUser(admin);
        User hello=basicService.queryUniqueUser(username);
        return hello;
    }
}
