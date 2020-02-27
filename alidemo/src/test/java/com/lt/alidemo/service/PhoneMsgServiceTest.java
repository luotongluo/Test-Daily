package com.lt.alidemo.service;

import com.lt.alidemo.AlidemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * Created by tong.luo on 2020/2/27 23:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlidemoApplication.class})
public class PhoneMsgServiceTest {

    @Autowired
    private PhoneMsgService phoneMsgService;

    @Test
    public void test() throws Exception {
        /*
        发送短信验证码
         */
        HashMap<String, String> reqMap = new HashMap<>(16);
        //参数，多个参数以‘|’分隔：如1234|1234
        reqMap.put("param", "哇卡卡卡卡卡");
        reqMap.put("phone", "13468759376");
        //签名编号【联系旺旺客服申请，测试请用1】
        reqMap.put("sign", "1");
        //模板编号【联系旺旺客服申请，测试请用1~21】
        reqMap.put("skin", "1");
        reqMap.put("path", "/codeNotice");
        reqMap.put("host", "https://feginesms.market.alicloudapi.com");
        this.phoneMsgService.sendPhoneMsg(reqMap);

    }

    @Test
    public void testSendContent() throws Exception {
        /*
        发送短信
         */
        HashMap<String, String> reqMap = new HashMap<>(16);
        //参数，多个参数以‘|’分隔：如1234|1234
        reqMap.put("param", "11223344|123123123");
        reqMap.put("phone", "13468759376");
        //签名编号【联系旺旺客服申请，测试请用1】
        reqMap.put("sign", "1");
        //模板编号【联系旺旺客服申请，测试请用1~21】
        reqMap.put("skin", "1");
        reqMap.put("path", "/smsmsgs");
        reqMap.put("host", "http://smsmsgs.market.alicloudapi.com");
        this.phoneMsgService.sendPhoneMsg(reqMap);

    }

}