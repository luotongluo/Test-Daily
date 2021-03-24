package com.lt.alidemo.service;

import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/3/4 17:08
 * @Description:
 * @Version 1.0
 */
public interface IdVerifyService {
    /**
     * 失信人和被执行人验证
     *
     * @param reqMap
     * @return
     */
    public String selectVerifyName(Map reqMap);
}
