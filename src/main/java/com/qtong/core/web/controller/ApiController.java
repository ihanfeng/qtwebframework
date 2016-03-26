package com.qtong.core.web.controller;

import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.service.BaseService;
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
    private BaseService baseService;


    @RequestMapping("/hello")

    public
    @ResponseBody
    User sayHello() {

        User admin = new User();
        admin.setUsername("hello" + System.currentTimeMillis());
        admin.setPassword("hello");
        Set<Role> roles = new HashSet<>();
        Role system = baseService.getRoleByRoleName("system");
        roles.add(system);
        admin.setRoles(roles);
        baseService.createUser(admin);
        return admin;
    }
}
