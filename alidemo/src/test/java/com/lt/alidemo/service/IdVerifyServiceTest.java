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
 * @Date: 2020/3/4 17:11
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlidemoApplication.class})
public class IdVerifyServiceTest {
    @Autowired
    private IdVerifyService idVerifyService;

    @Test
    public void testIdVerify() {
        HashMap<String, String> reqMap = new HashMap<>(16);
        reqMap.put("host", "http://bzxrandsxr.market.alicloudapi.com");
        reqMap.put("path", "/ws/repository/dishonestyAndExecutedSum");
        //查询类型，1:个人;2:企业/机构，必填
        reqMap.put("btype", "2");
        //主体代码,当失信人被执行人类型为个人时，该字段必填
        reqMap.put("entityId", "2");
        //主体名称,必填
        reqMap.put("entityName", "华为技术有限公司");
        //请填写 6tj4uYYYYMMDD的32位md5加密值，例6tj4u20150715的ｍd5加密值为 c65ab8aa265bb6351a9224cd13aade29，加密工具地址：http://www.cmd5.com/
        reqMap.put("sign", "2");
        this.idVerifyService.selectVerifyName(reqMap);
    }

}