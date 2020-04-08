package com.lt.activit.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.activit.activiti.service.TestService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.NativeProcessDefinitionQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/12/3 21:18
 * @Description:
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;

    @Override
    public void activiti() {
        // 获得一个部署构建器对象，用于加载流程定义文件（test1.bpmn,test.png）完成流程定义的部署
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder builder = processEngine.getRepositoryService().createDeployment();
        // 加载流程定义文件
        builder.addClasspathResource("TestApprove.bpmn")
        .deploy();

    }

    @Override
    public void startProcesses(String bizId) {
        /*
        根据流程定义的Id启动一个流程实例(操作ACT_RU_EXECUTION、ACT_RU_TASK、
        ACT_HI_PROCINST、ACT_HI_ACTINST、ACT_HI_TASKINST、ACT_RU_IDENTITYLINK、
        ACT_HI_IDENTITYLINK表)
         */
//        String processDefinitionId="qjlc:1:104";
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByDeploymentId().asc();
        //分页查询
        query.listPage(0, 2);
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition item : list) {
            System.out.println(item.getId());
            ProcessInstance processInstance = null;
            try {
                processInstance = runtimeService.startProcessInstanceById(item.getId());
            } catch (Exception e) {
                HashMap<String, Object> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("TL", "TLApprove");
                try {
                    runtimeService.startProcessInstanceById(item.getId(), stringStringHashMap);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } finally {

            }
            System.out.print(processInstance.getId());//201
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(bizId);
        System.out.print(processInstance.getId());//201

    }

    @Override
    public void getQueryList() {
        //流程定义查询对象，用于查询act_re_procdef表
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        //添加查询条件
//        query.processDefinitionKey("bxlc");
        query.orderByDeploymentId().desc();
        //分页查询
        query.listPage(0, 10);
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition item : list) {
            System.out.println(item.getId());
        }
//        logger.info("items-》{}",JSON.toJSONString(list));


    }

    @Override
    public void getSomeOnejobs() {
        TaskQuery query = taskService.createTaskQuery();
//        query.taskAssignee("张三");
        List<Task> list = query.list();
        for (Task item : list) {
            System.out.println(item.getId() + "===" + item.getName());//204===提交请假申请
        }

    }

    @Override
    public void doSomeOnejobs() {
//        taskService.complete("15003");
        TaskQuery query = taskService.createTaskQuery();
//        query.taskAssignee("张三");
        List<Task> list = query.list();
        for (Task item : list) {
            System.out.println(item.getId() + "===" + item.getName());//204===提交请假申请
            taskService.complete(item.getId());
        }
    }

    @Override
    public void historyData() {
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();
        HistoricActivityInstanceQuery instanceQuery = query.orderByActivityId().orderByHistoricActivityInstanceEndTime().asc();
        logger.info("historyData --》{}", JSON.toJSONString(instanceQuery));
    }

    @Override
    public void doApproveByAllStep() {
        /*
        部署、启动、完成、历史
         */
        DeploymentBuilder builder = repositoryService.createDeployment();
        // 加载流程定义文件
        Deployment deploy = builder
                .name("TestApprove")
                .addClasspathResource("TestApprove.bpmn")
                .deploy();

        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition testApprove = definitionQuery.processDefinitionId(deploy.getId()).singleResult();
        runtimeService.startProcessInstanceById(testApprove.getId(), "TL");

        List<Task> list = taskService.createTaskQuery().list();
        for (Task task : list) {
            String taskId = task.getId();
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(taskId);
//            HashMap<String, String> stringStringHashMap = new HashMap<>();
//            runtimeService.startProcessInstanceById(taskId,)

        }

    }
}
