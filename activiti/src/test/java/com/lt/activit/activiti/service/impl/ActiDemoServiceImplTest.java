package com.lt.activit.activiti.service.impl;

import com.lt.activit.activiti.service.ActiDemoService;
import com.lt.activit.activiti.vo.ActiReqVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description ActiDemoServiceImplTest
 * @date 2021/3/23 15:55
 */
@SpringBootTest
public class ActiDemoServiceImplTest {
    @Autowired
    private ActiDemoService actiDemoService;

    @Test
    void createDemoActi() {
        ActiReqVo actiReqVo = new ActiReqVo();
        actiReqVo.setResourcePath("progress/demo.bpmn");
        actiReqVo.setBusinessKey(System.currentTimeMillis() + "");
        this.actiDemoService.createDemoActi(actiReqVo);
    }

    @Test
    void startDemoActi() {
        ActiReqVo actiReqVo = new ActiReqVo();
        actiReqVo.setBusinessKey("financialReport");
        this.actiDemoService.startDemoActi(actiReqVo);
    }
}