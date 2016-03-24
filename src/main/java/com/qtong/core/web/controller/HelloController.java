package com.qtong.core.web.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZML on 2016/3/22.
 */

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(path = "/welcome")
    public ModelAndView welcome(ModelAndView modelAndView) {

        // int i=1/0;
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
