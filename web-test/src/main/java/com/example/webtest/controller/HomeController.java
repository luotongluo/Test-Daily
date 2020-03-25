package com.example.webtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luotong
 * @description HomeController
 * @date 2020/3/25 11:27
 */
@RestController
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

}
