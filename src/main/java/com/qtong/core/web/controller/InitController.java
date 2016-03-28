package com.qtong.core.web.controller;

import com.qtong.core.model.Permission;
import com.qtong.core.model.Role;
import com.qtong.core.model.User;
import com.qtong.core.service.BasicService;
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

    private BasicService basicService;

    @Resource
    public void setBasicService(BasicService basicService) {
        this.basicService = basicService;
    }

    @RequestMapping("/init")
    private ModelAndView initDatabase(ModelAndView modeAndView) {
        if (basicService.listAllPermissions() == null || basicService.listAllPermissions().size()==0 ) {
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
        Role system = basicService.getRoleByRoleName(SYSTEM);
        roles.add(system);
        admin.setRoles(roles);
        basicService.createUser(admin);
    }

    private void initRoles() {
        Role system = new Role();
        system.setRoleName(SYSTEM);
        system.setPermissions(basicService.listAllPermissions());
        basicService.saveOrUpdateBean(system);
    }

    private void initPermissions() {
        Permission system = new Permission();
        system.setExpression(SYSTEM);
        system.setName("system");
        basicService.saveOrUpdateBean(system);
    }
}
