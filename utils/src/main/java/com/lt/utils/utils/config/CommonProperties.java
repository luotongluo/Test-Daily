package com.lt.utils.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: LT
 * @Date: 2019/10/31 19:40
 * @Description:
 * @Version 1.0
 */
@Component
@RefreshScope
public class CommonProperties {
    @Value("${com.test}")
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
