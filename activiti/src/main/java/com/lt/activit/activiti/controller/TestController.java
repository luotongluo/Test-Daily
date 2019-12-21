package com.lt.activit.activiti.controller;

import com.lt.activit.activiti.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/12/3 22:02
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public Map test() {
        return null;
    }
}
