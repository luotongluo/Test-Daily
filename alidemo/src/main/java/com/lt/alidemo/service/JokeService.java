package com.lt.alidemo.service;

import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/2/27 16:41
 * @Description:
 * @Version 1.0
 */
public interface JokeService {
    /**
     * 查询文字笑话
     *
     * @param reqmap
     * @return
     * @throws Exception
     */
    public String getTextJoke(Map reqmap) throws Exception;
}
