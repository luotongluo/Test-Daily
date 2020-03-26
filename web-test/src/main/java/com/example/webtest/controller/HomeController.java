package com.example.webtest.controller;

import com.example.webtest.service.TestSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author luotong
 * @description HomeController
 * @date 2020/3/25 11:27
 */
@RestController
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private TestSqlService testSqlService;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping("getSqlRet")
    public String getSqlRet() {
       return this.testSqlService.testActiveSql(new HashMap());
    }
}
