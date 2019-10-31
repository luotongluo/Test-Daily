package com.lt.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/10/30 20:42
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("test")
    public Map test(@RequestBody String ss){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code",ss);
        return hashMap;
    }

}
