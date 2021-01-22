package com.lt.demo.controller;

import com.lt.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description DemoController
 * @date 2021/1/22 13:48
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("directDemo")
    public String directDemo() {
        this.demoService.directDemo();
        return "SUCCESS";
    }

    @RequestMapping("topicDemo")
    public String topicDemo() {
        this.demoService.topicDemo();
        return "SUCCESS";
    }

    @RequestMapping("fanoutDemo")
    public String fanoutDemo() {
        this.demoService.fanoutDemo();
        return "SUCCESS";
    }
}
