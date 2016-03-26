package com.qtong.core.web.controller;

import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZML on 2016/3/26.
 */
@Controller
@RequestMapping("/mng")
public class InitController {
    //系统最高权限
    private static final String SYSTEM = "system";

    private BaseService baseService;

    @Resource
    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    @RequestMapping("/init")
    private ModelAndView initDatabase(ModelAndView modeAndView) {
        if (baseService.listAllPermissions() != null) {
            initPermissions();
            initRoles();
            createAdministrator();
        }

        modeAndView.setViewName("redirect:/");
        return modeAndView;
    }

    private void createAdministrator() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<Role> roles = new HashSet<>();
        Role system = baseService.getRoleByRoleName(SYSTEM);
        roles.add(system);
        admin.setRoles(roles);
        baseService.createUser(admin);
    }

    private void initRoles() {
        Role system = new Role();
        system.setRoleName(SYSTEM);
        system.setPermissions(baseService.listAllPermissions());
        baseService.create(system);
    }

    private void initPermissions() {
        Permission system = new Permission();
        system.setExpression(SYSTEM);
        system.setName("system");
        baseService.create(system);
    }
}
