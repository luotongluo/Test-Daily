package com.lt.alidemo.service;

import java.util.Map;

/**
 * Created by tong.luo on 2020/2/27 23:16
 */
public interface PhoneMsgService {
    /**
     * 短信发送
     *
     * @param reqMaq
     * @return
     * @throws Exception
     */
    public String sendPhoneMsg(Map reqMaq) throws Exception;
}
