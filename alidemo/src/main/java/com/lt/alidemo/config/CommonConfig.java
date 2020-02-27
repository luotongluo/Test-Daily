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
@Component
@RefreshScope
public class CommonConfig {
    @Value("${url.yiyuan.appkey}")
    private String yiyuanappkey;

    @Value("${url.yiyuan.AppSecret}")
    private String yiyuanAppSecret;

    @Value("${url.yiyuan.AppCode}")
    private String yiyuanAppCode;

    public String getYiyuanappkey() {
        return yiyuanappkey;
    }

    public void setYiyuanappkey(String yiyuanappkey) {
        this.yiyuanappkey = yiyuanappkey;
    }

    public String getYiyuanAppSecret() {
        return yiyuanAppSecret;
    }

    public void setYiyuanAppSecret(String yiyuanAppSecret) {
        this.yiyuanAppSecret = yiyuanAppSecret;
    }

    public String getYiyuanAppCode() {
        return yiyuanAppCode;
    }

    public void setYiyuanAppCode(String yiyuanAppCode) {
        this.yiyuanAppCode = yiyuanAppCode;
    }
}
