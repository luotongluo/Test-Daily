package com.lt.activit.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.activit.activiti.service.TestService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/12/3 21:18
 * @Description:
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void activiti() {
        // 获得一个部署构建器对象，用于加载流程定义文件（test1.bpmn,test.png）完成流程定义的部署
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder builder = processEngine.getRepositoryService().createDeployment();
        // 加载流程定义文件
        builder.addClasspathResource("TestBmpn.bpmn");
//        builder.addClasspathResource("process/test1.png");
        // 部署流程定义
        Deployment deployment = builder.deploy();
        System.out.println(JSON.toJSONString(deployment));

    }

    @Override
    public void startProcesses(String bizId) {
        /*
        根据流程定义的Id启动一个流程实例(操作ACT_RU_EXECUTION、ACT_RU_TASK、
        ACT_HI_PROCINST、ACT_HI_ACTINST、ACT_HI_TASKINST、ACT_RU_IDENTITYLINK、
        ACT_HI_IDENTITYLINK表)
         */
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        String processDefinitionId="qjlc:1:104";
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(bizId);
        System.out.print(processInstance.getId());//201

    }

    @Override
    public void getQueryList() {
        //流程定义查询对象，用于查询act_re_procdef表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
        //添加查询条件
//        query.processDefinitionKey("bxlc");
        query.orderByDeploymentId().desc();
        //分页查询
        query.listPage(0, 10);
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition item : list) {
            System.out.println(item.getId());
        }

    }

    @Override
    public void getSomeOnejobs() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskQuery query = processEngine.getTaskService().createTaskQuery();
//        query.taskAssignee("张三");
        List<Task> list = query.list();
        for (Task item : list) {
            System.out.println(item.getId()+"==="+item.getName());//204===提交请假申请
        }

    }

    @Override
    public void doSomeOnejobs() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService().complete("40005");
    }
}
