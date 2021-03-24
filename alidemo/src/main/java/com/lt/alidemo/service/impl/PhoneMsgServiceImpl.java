package com.lt.alidemo.service.impl;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.service.PhoneMsgService;
import com.lt.alidemo.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tong.luo on 2020/2/27 23:16
 */
@Service
public class PhoneMsgServiceImpl implements PhoneMsgService {
    @Autowired
    private CommonConfig commonConfig;

    @Override
    public String sendPhoneMsg(Map reqMaq) throws Exception {
        String host = (String) reqMaq.get("host");
        String path = (String) reqMaq.get("path");
        String method = "GET";
        String appcode = "你自己的AppCode";
        appcode = this.commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("param", (String) reqMaq.get("param"));
        querys.put("phone", (String) reqMaq.get("phone"));
        querys.put("sign", (String) reqMaq.get("sign"));
        querys.put("skin", (String) reqMaq.get("skin"));
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        /**
         * 重要提示如下:
         * HttpUtils请从
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
         * 或者直接下载：
         * http://code.fegine.com/HttpUtils.zip
         * 下载
         *
         * 相应的依赖请参照
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
         * 相关jar包（非pom）直接下载：
         * http://code.fegine.com/aliyun-jar.zip
         */
        HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
        System.out.println(response);
        //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
        //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
        //获取response的body
        System.out.println(EntityUtils.toString(response.getEntity()));
        return null;
    }
}
