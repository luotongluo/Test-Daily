package com.lt.alidemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description WeatherControllerTest
 * @date 2020/4/11 12:49
 */
@SpringBootTest
class WeatherControllerTest {
    @Autowired
    private WeatherController weatherController;

    @Test
    public void testWeather() {
        HashMap<String, String> reqMap = new HashMap<>(16);
        reqMap.put("area", "");
        reqMap.put("areaid", "");
        //月份
        reqMap.put("month", "");
        //地址
        reqMap.put("path", "/weatherhistory");
        this.weatherController.testWeather(reqMap);
    }

}