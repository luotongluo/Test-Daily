package com.lt.alidemo.service.impl;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.service.JokeService;
import com.lt.alidemo.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/2/27 16:42
 * @Description:
 * @Version 1.0
 */
@Service
public class JokeServiceimpl implements JokeService {
    private static Logger logger = LoggerFactory.getLogger(JokeServiceimpl.class);
    private final String HOSTS = "https://jisuxhdq.market.alicloudapi.com";

    @Autowired
    private CommonConfig commonConfig;

    @Override
    public String getTextJoke(Map reqmap) throws Exception {
        String host = HOSTS;
        String path = (String) reqmap.get("path");
        String method = "GET";
        String appcode = "你自己的AppCode";
        appcode = this.commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("pagenum", (String) reqmap.get("pagenum"));
        querys.put("pagesize", (String) reqmap.get("pagesize"));
        querys.put("sort", (String) reqmap.get("sort"));

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
