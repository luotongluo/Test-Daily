package com.lt.alidemo.controller;

import com.lt.alidemo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    /**
     * 天气接口调用测试
     * @param reqMap
     * @return
     */
    @RequestMapping("weather")
    public String testWeather(@RequestBody Map reqMap) {
        try {
            return this.weatherService.testWeather(reqMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
