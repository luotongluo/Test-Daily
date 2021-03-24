package com.lt.alidemo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author tong.luo
 * @description Test
 * @date 2020/7/30 0:14
 */
@RestController
public class TestController {

    @RequestMapping("testloop")
    public String test(@RequestParam("loop") Integer loop,
                       @RequestParam(value = "Loopparam", required = false) String param,
                       @RequestParam(value = "type", required = false) String type) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("copy /b ");
//        int loop = loop;

        String a1 = "Y2hlbmppbmdjb25n";
        if (StringUtils.isNotEmpty(param)) {
            a1 = param;
        }
//                     Y2hlbmppbmdjb25n0
        for (int i = 0; i <= loop; i++) {
            if (i == loop) {
                stringBuffer.append(a1 + i);
            } else {
                stringBuffer.append(a1 + i + "+");

            }
        }
        UUID uuid = UUID.randomUUID();
        String substring = String.valueOf(uuid).substring(0, 10);
        if (StringUtils.isNotEmpty(type)) {

        } else {
            type = "mp4";
        }

        stringBuffer.append(" " + substring + "." + type);
        System.out.println(stringBuffer);
        return stringBuffer.toString();
    }
}
