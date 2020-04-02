package com.lt.alidemo;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/2/27 15:14
 * @Description:
 * @Version 1.0
 */
@Component
public class TestYiYuanWeather {
    @Autowired
    private CommonConfig commonConfig;

    public static void main(String[] args) {
        TestYiYuanWeather testYiYuanWeather = new TestYiYuanWeather();
        testYiYuanWeather.testYiYuanWeaTher();
    }

    public  void testYiYuanWeaTher(){
        String host = "https://ali-weather.showapi.com";
        String path = "/weatherhistory";
        String method = "GET";
        String appcode = "1c593543dff4405bb82256bf2f0be197";
//        appcode = this.commonConfig.getYiyuanAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", "丽江");
        querys.put("areaid", "101291401");
        querys.put("month", "202002");


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
            /*
            res:HTTP/1.1 200 OK [Server: Tengine, Date: Thu, 27 Feb 2020 07:37:06 GMT,
            Content-Type: application/json;charset=utf-8, Content-Length: 6083,
            Connection: keep-alive, Vary: Accept-Encoding, Access-Control-Allow-Origin: *,
            Access-Control-Allow-Methods: GET, POST, OPTIONS, Access-Control-Allow-Headers: *,
            Access-Control-Max-Age: 1728000, X-Ca-Request-Id: 7D21103B-7901-4ABC-B448-BCB14E8881D2,
            X-Ca-Error-Message: OK, showapi_fee_num: 1, Access-Control-Allow-Credentials: true]
            org.apache.http.conn.BasicManagedEntity@5745ca0e
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
