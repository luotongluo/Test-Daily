package com.lt.activit.activiti.controller;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luotong
 * @description TestControllerTest
 * @date 2020/4/16 17:54
 */
@SpringBootTest
class TestControllerTest {
    @Autowired
    private TestController testController;

    @Test
    void activiti() {
        Map activiti = testController.activiti();
        System.out.println(JSON.toJSONString(activiti));
    }

    @Test
    void startProcesses() {
        Map map = null;
        try {
            map = this.testController.startProcessesByKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    void delApproveInfo() {
        Map map = this.testController.delApproveInfo();
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    void startProcessesByKey() {
        try {
            Map startProcessesByKey = this.testController.startProcessesByKey();
            System.out.println(JSON.toJSONString(startProcessesByKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}