package com.lt.activit.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.activit.activiti.service.TestService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private IdentityService identityService;

    @Override
    public void activiti() {
        /*
        SELECT * FROM `act_re_procdef`;  ---- 流程定义表
SELECT * FROM `act_re_deployment`; ---- 部署表
SELECT * FROM `act_ge_property`;  --- 通用属性表 id生成策略 next.dbid 影响部署的id
         */
        // 获得一个部署构建器对象，用于加载流程定义文件（test1.bpmn,test.png）完成流程定义的部署
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder builder = repositoryService.createDeployment();
        // 加载流程定义文件
        UUID uuid = UUID.randomUUID();
        Deployment deploy = builder
                .name(String.valueOf(uuid))
                .addClasspathResource("progress/TestBmpn.bpmn")
                ////设置部署类别
                .category("测试类别")
                .deploy();
        System.out.println("success");
        logger.info("WorkFlowServiceImpl-->deploymentProcessDefinition-->end.. ,deploymentID:{},deploymentName:{}"
                , deploy.getId(), deploy.getName());
    }

    @Override
    public void startProcesses(String bizId) {
        /*
        根据流程定义的Id启动一个流程实例(操作ACT_RU_EXECUTION、ACT_RU_TASK、
        ACT_HI_PROCINST、ACT_HI_ACTINST、ACT_HI_TASKINST、ACT_RU_IDENTITYLINK、
        ACT_HI_IDENTITYLINK表)
         */

        // 流程定义查询对象，查询表 act_re_procdef
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByDeploymentId().active().asc();
        //分页查询
        query.listPage(0, 10);
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition item : list) {
            System.out.println(item.getName() + "" + item.getId());
            ProcessInstance processInstance = null;
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("oneLevel", "123");
            processInstance = runtimeService.startProcessInstanceById(item.getId(), "TestBmpn", stringObjectHashMap);
            logger.info("流程启动成功 id :{}", processInstance == null ? "" : processInstance.getId());
        }
    }

    @Override
    public void createReviewProcess() {
        String bussnessCode = "";
        String operator = "";
        String processDefinitionKey = "";
        HashMap<String, Object> variables = new HashMap<>();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().
                processInstanceBusinessKey(bussnessCode).singleResult();
        if (null != processInstance) {
            return;
        }
        identityService.setAuthenticatedUserId(operator);
        ProcessInstance pi = runtimeService//与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey, bussnessCode, variables);
        logger.info(" end processDefinitionKey:{}," +
                "ProcessInstanceID:{}", processDefinitionKey, pi.getId());
    }

    @Override
    public void findTasksByUserId() {
        String userId ="dulingjiang";
        List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("TestBmpn").taskCandidateOrAssigned(userId).list();
        System.out.println("任务列表："+resultTask);

    }

    @Override
    public void startApproveByAssige() {
        /*
        根据流程定义的Id启动一个流程实例(操作ACT_RU_EXECUTION、ACT_RU_TASK、
        ACT_HI_PROCINST、ACT_HI_ACTINST、ACT_HI_TASKINST、ACT_RU_IDENTITYLINK、
        ACT_HI_IDENTITYLINK表)
         */
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByDeploymentId().active().asc();
        //分页查询
        query.listPage(0, 10);
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition item : list) {
            System.out.println(item.getId());
            ProcessInstance processInstance = null;
            HashMap<String, Object> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("TL", "TLApprove");
            try {
                runtimeService.startProcessInstanceById(item.getId(), stringStringHashMap);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {

            }
            logger.info("id :{}", processInstance == null ? "" : JSON.toJSONString(processInstance));

        }
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
//        query.taskAssignee("123");

        List<Task> list = query.list();
        for (Task item : list) {
            System.out.println(item.getId() + "===" + item.getName());//204===提交请假申请
        }

    }

    @Override
    public void doSomeOnejobs() {
//        taskService.complete("15003");
        TaskQuery query = taskService.createTaskQuery();
//        query.taskAssignee("TL");
        List<Task> list = query.list();
        for (Task item : list) {
            System.out.println(item.getId() + "===" + item.getName());//204===提交请假申请
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("oneLevel", "TL");
            hashMap.put("day", "5");
            taskService.complete(item.getId(), hashMap);
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
                .addClasspathResource("progress/TestApprove.bpmn")
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

    @Override
    public void delApproveInfo() {
        // 部署查询对象，查询表act_re_deployment
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        List<Deployment> list = query.list();
        for (Deployment deployment : list) {
            repositoryService.deleteDeployment(deployment.getId(), true);
            logger.info("deploymentId ---->{},deploymentname-->{}", deployment.getId(), deployment.getName());
        }

    }

    @Override
    public void delApproveDely() {
        // 流程定义查询对象，查询表act_re_procdef
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition pd : list) {
            repositoryService.deleteDeployment(pd.getDeploymentId(),
                    true);
            System.out.println(pd.getName() + "" + pd.getDeploymentId());
        }

    }

    @Override
    public void getDeployList() {
        // 部署查询对象，查询表act_re_deployment
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        List<Deployment> list = query.list();
        for (Deployment deployment : list) {
            logger.info("deploymentId ---->{},deploymentname-->{}", deployment.getId(), deployment.getName());
        }
    }
}
