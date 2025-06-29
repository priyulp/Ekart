package com.reljicd.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public ModelAndView handleError() {
        return new ModelAndView("error"); // maps to src/main/resources/templates/error.html
    }

    @GetMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("403"); // maps to src/main/resources/templates/403.html
    }

    // getErrorPath is deprecated and not needed anymore in Spring Boot 2.3+
    // So we can safely omit overriding it.
}
