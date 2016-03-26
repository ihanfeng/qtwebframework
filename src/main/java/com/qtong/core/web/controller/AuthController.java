package com.qtong.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZML on 2016/3/26.
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("main");
        return modelAndView;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
