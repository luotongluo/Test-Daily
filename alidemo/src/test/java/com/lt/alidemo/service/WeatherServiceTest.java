package com.lt.alidemo.service;

import com.lt.alidemo.AlidemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @Author: LT
 * @Date: 2020/2/27 16:18
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlidemoApplication.class})
public class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void testWeather() throws Exception {
        HashMap<String, String> reqMap = new HashMap<>(16);
        reqMap.put("area", "");
        reqMap.put("areaid", "");
        reqMap.put("month", "");
        reqMap.put("path", "/weatherhistory");
        this.weatherService.testWeather(reqMap);
    }

    @Test
    public void hour24() throws Exception {
        HashMap<String, String> reqMap = new HashMap<>(16);
        //下面参数二选一 或者二者都可
        reqMap.put("area", "北京");
//        reqMap.put("areaid", "");
        reqMap.put("path", "/hour24");
        /*
        {
                  "showapi_res_error": "",
                  "showapi_res_id": "affd64bf3014440e9d15d25d77fe1076",
                  "showapi_res_code": 0,
                  "showapi_res_body": {"ret_code":0,"area":"北京","areaCode":"110000","showapi_fee_code":-1,"areaid":"101010100","hourList":[{"weather_code":"06","time":"202002271600","area":"北京","wind_direction":"西南风","wind_power":"3-4级 微风  5.5~7.9m/s","areaid":"101010100","weather":"雨夹雪","temperature":"4"},{"weather_code":"06","time":"202002271700","area":"北京","wind_direction":"西南风","wind_power":"3-4级 微风  5.5~7.9m/s","areaid":"101010100","weather":"雨夹雪","temperature":"4"},{"weather_code":"06","time":"202002271800","area":"北京","wind_direction":"西南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"雨夹雪","temperature":"2"},{"weather_code":"06","time":"202002271900","area":"北京","wind_direction":"西南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"雨夹雪","temperature":"1"},{"weather_code":"06","time":"202002272000","area":"北京","wind_direction":"西南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"雨夹雪","temperature":"1"},{"weather_code":"02","time":"202002272100","area":"北京","wind_direction":"西南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"0"},{"weather_code":"02","time":"202002272200","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"0"},{"weather_code":"01","time":"202002272300","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"多云","temperature":"-1"},{"weather_code":"02","time":"202002280000","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-1"},{"weather_code":"02","time":"202002280100","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-1"},{"weather_code":"02","time":"202002280200","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280300","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280400","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280500","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280600","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280700","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"02","time":"202002280800","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"阴","temperature":"-2"},{"weather_code":"01","time":"202002280900","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"多云","temperature":"-1"},{"weather_code":"01","time":"202002281000","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"多云","temperature":"1"},{"weather_code":"00","time":"202002281100","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"晴","temperature":"4"},{"weather_code":"00","time":"202002281200","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"晴","temperature":"5"},{"weather_code":"00","time":"202002281300","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"晴","temperature":"7"},{"weather_code":"00","time":"202002281400","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"晴","temperature":"8"},{"weather_code":"00","time":"202002281500","area":"北京","wind_direction":"南风","wind_power":"0-3级 微风  <5.4m/s","areaid":"101010100","weather":"晴","temperature":"8"}]}
                }
         */
        this.weatherService.hour24(reqMap);
    }

}