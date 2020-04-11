package com.lt.activit.activiti.service;

import com.lt.activit.activiti.ActivitiApplication;
import com.lt.activit.activiti.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Author: LT
 * @Date: 2019/12/3 21:24
 * @Description:
 * @Version 1.0
 */
@SpringBootTest(classes = {ActivitiApplication.class, TestServiceTest.class})
public class TestServiceTest {
    @Autowired
    private TestServiceImpl testService;


    @Test
    public void activiti() {
        this.testService.activiti();
    }

    @Test
    public void startProcesses() {
        this.testService.startProcesses(null);
    }

    @Test
    public void getQueryList() {
        this.testService.getQueryList();
    }

    @Test
    public void getSomeOnejobs() {
        this.testService.getSomeOnejobs();
    }

    @Test
    public void doSomeOnejobs() {
        this.testService.doSomeOnejobs();
    }

    @Test
    public void historyData() {
        this.testService.historyData();
    }

    @Test
    public void doApproveByAllStep() {
        this.testService.doApproveByAllStep();
    }

    @Test
    public void delApproveInfo() {
        this.testService.delApproveInfo();
    }

    @Test
    public void getDeployList() {
        this.testService.getDeployList();
    }
    @Test
    public void delApproveDely() {
        this.testService.delApproveDely();
    }


}