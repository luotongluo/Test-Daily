package com.lt.activit.activiti;

import com.lt.activit.activiti.service.TestService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class, ActivitiApplicationTests.class})
class ActivitiApplicationTests {
    @Autowired
    private TestService testService;
    @Autowired
    private ProcessEngine processEngine;

    @Test
    void contextLoads() {
    }

    /**
     * 从配置文件中读取配置，测试Activiti数据库环境是否建立
     */
    @Test
    public void testActivitiEnvironment2() {

        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("/activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
    }

    @Test
    public void startProcesses() {
        this.testService.startProcesses("123");
    }

    @Test
    public void start() {
        this.testService.start();
    }

    //部署流程
    @Test
    public void deployProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("TestAct.bpmn");//bpmn文件的名称
        builder.deploy();
    }

}
