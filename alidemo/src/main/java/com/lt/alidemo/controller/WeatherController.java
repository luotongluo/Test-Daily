package com.lt.alidemo.controller;

import com.lt.alidemo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LT
 * @Date: 2020/2/27 15:43
 * @Description: 天气的测试使用类
 * @Version 1.0
 */
@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    public String testWeather() {
        return null;
    }
}
