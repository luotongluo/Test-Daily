package com.lt.alidemo.utils;

import com.lt.alidemo.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/3/4 15:33
 * @Description:
 * @Version 1.0
 */
@Component
public class CommonCode {
    @Autowired
    private static CommonConfig commonConfig;

    public CommonCode() {
    }


    public static void starthead(Map map) {
        //API产品路径
        String host = (String) map.get("host");
        String path = (String) map.get("path");
        String method = "GET";
        //阿里云APPCODE
        String appcode = "你自己的AppCode";
        appcode = commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
    }
}
