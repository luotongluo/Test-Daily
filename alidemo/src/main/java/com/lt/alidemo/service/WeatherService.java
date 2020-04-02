package com.lt.alidemo.service;

import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/2/27 15:44
 * @Description:
 * @Version 1.0
 */
public interface WeatherService {
    /**
     * 天气接口调用测试
     *
     * @param reqMap
     * @return
     */
    String testWeather(Map reqMap) throws Exception;

    /**
     * id或地名查询24小时预报
     *
     * @param reqMap
     * @return
     */
    String hour24(Map reqMap) throws Exception;
}
