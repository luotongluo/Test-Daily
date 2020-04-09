package com.lt.activit.activiti.service;

import com.lt.activit.activiti.ActivitiApplication;
import com.lt.activit.activiti.service.impl.TestServiceImpl;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @Author: LT
 * @Date: 2019/12/3 21:24
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class, TestServiceTest.class})
class TestServiceTest {
    @Autowired
    private TestServiceImpl testService;

    @Test
    public void testDeployFromClasspath() {
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .name("EnglishTesk")
                .activateProcessDefinitionsOn(new Date())
                //以文本方式打开 process中的内容不能存在空的
                .addClasspathResource("EnglishTesk.bpmn")
//                .addClasspathResource("TestAct.png")
                .deploy();
    }

    @Test
    public void creatActivitiTask() {
//        this.testService.creatActivitiTask("EnglishTesk.bpmn","oneTest");
//        this.testService.testStartProcessInstance();
//        this.testService.testQingjia();
//        String assgn = "big-mall2";
//        this.testService.testQueryTask(assgn);
//        this.testService.testFinishTaskManager();
//        this.testService.getQueryList();
        /*
        myProcess_1:3:37504
myProcess_1:2:32504
myProcess_1:1:22504
         */
//        this.testService.startProcesses("myProcess_1:3:37504");
//        testService.getSomeOnejobs();
        testService.doSomeOnejobs();
    }

}