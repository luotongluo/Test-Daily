package com.lt.utils.utils.test;

import com.lt.utils.utils.config.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LT
 * @Date: 2019/10/31 19:43
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CommonProperties commonProperties;

    @RequestMapping("test")
    public String test(String string) {
        String test = commonProperties.getTest();
        logger.info(test);
        return string;
    }

}
