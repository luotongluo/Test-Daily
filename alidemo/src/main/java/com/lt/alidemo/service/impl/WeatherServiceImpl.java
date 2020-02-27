package com.lt.alidemo.service.impl;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.service.WeatherService;
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
 * @Date: 2020/2/27 15:44
 * @Description:
 * @Version 1.0
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    private static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final  String HOSTS = "https://ali-weather.showapi.com";
    @Autowired
    private CommonConfig commonConfig;

    @Override
    public String testWeather(Map reqMap) throws Exception {
        String host = HOSTS;
        String path = (String) reqMap.get("path");
        String method = "GET";
//        String appcode = "1c593543dff4405bb82256bf2f0be197";
        String appcode = this.commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("area", "丽江");
        querys.put("area", (String)reqMap.get("area"));
//        querys.put("areaid", "101291401");
        querys.put("areaid", (String)reqMap.get("areaid"));
//        querys.put("month", "202002");
        querys.put("month", (String)reqMap.get("month"));
        HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
        System.out.println(response.toString());
        logger.info("host:{},path:{},headers:{},method:{},querys:{}",
                host,path,headers,method,querys);
        //获取response的body
        String res = EntityUtils.toString(response.getEntity());
        logger.info("res:{}",res);
        return res;
    }

    @Override
    public String hour24(Map reqMap) throws Exception {
        String host = HOSTS;
        String path = (String) reqMap.get("path");
        String method = "GET";
        String appcode = "你自己的AppCode";
        appcode = this.commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("area", "丽江");
        querys.put("area", (String) reqMap.get("area"));
//        querys.put("areaid", "101230506");
        querys.put("areaid", (String) reqMap.get("areaid"));


        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//            System.out.println(response.toString());
            logger.info("host:{},path:{},headers:{},method:{},querys:{}",
                    host,path,headers,method,querys);
            //获取response的body
            String res = EntityUtils.toString(response.getEntity());
//            System.out.println(EntityUtils.toString(response.getEntity()));
            logger.info("res:{}",res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
