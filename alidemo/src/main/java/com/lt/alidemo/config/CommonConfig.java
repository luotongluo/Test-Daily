package com.lt.alidemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: LT
 * @Date: 2020/2/27 15:06
 * @Description: 公共的配置类
 * @Version 1.0
 */
@RefreshScope
@Component
public class CommonConfig {
    @Value("${url.appkey}")
    private String appkey;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}
