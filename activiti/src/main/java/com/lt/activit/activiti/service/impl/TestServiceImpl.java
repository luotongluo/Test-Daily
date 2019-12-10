package com.lt.activit.activiti.service.impl;

import com.lt.activit.activiti.service.TestService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessEngine processEngine;

    @Override
    public void activiti() {
        System.out.println("任务已经执行.....................................");
    }

    @Override
    public List<String> user() {
        return Arrays.asList("xiaoming", "xiaohong");
    }

    /**
     * 启动流程
     *
     * @param bizId 业务id
     */
    public void startProcesses(String bizId) {
//        TaskQuery taskQuery = taskService.createTaskQuery();
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(bizId);//流程图id，业务表id
        System.out.println("流程启动成功，流程id:" + pi.getId());
    }

    @Override
    public List<Task> findTasksByUserId(String userId) {
        return null;
    }

    @Override
    public void start() {

    }


    /**
     * 发布一个新的审批流
     */
    @Override
    public void creatActivitiTask(String fileName, String nameOfApprove) {
        //加载的那两个内容就是我们之前已经弄好的基础内容哦。
        //得到了流程引擎
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        processEngine.getRepositoryService()
//                .createDeployment()
////                .addClasspathResource("EnglishTesk.bpmn")
//                .addClasspathResource(fileName)
////                .addClasspathResource("shenqing.png")
//                .deploy();

        this.processEngine.getRepositoryService()
                .createDeployment()
                .name(nameOfApprove)
                .addClasspathResource(fileName).deploy();
        System.out.println("deploy successful……");
    }

    /**
     * 2：启动流程实例
     */
    @Override
    public void testStartProcessInstance() {
        //这个是查看数据库中act_re_procdef表
        processEngine.getRuntimeService()
                .startProcessInstanceById("myProcess_1:1:22504");
        System.out.println("启动流程实例 -- successful");
    }

    /**
     * 完成请假申请
     */
    @Override
    public void testQingjia() {
        //查看act_ru_task表
        processEngine.getTaskService()
                .complete("25005");
    }

    /**
     * 查询正在执行的任务
     */
    @Override
    public void testQueryTask(String assingee) {

        //下面代码中的小毛，就是我们之前设计那个流程图中添加的班主任内容
        List<Task> tasks = null;
        if (StringUtils.isNotEmpty(assingee)) {
            tasks = processEngine.getTaskService()
                    .createTaskQuery()
                    .taskAssignee(assingee)
                    .list();
        } else {
            tasks = processEngine.getTaskService()
                    .createTaskQuery()
                    .list();
        }
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }

    /**
     * 完成任务
     */
    @Override
    public void testFinishTaskManager() {
        //查看act_ru_task数据表
        processEngine.getTaskService()
                .complete("27502");
    }

    /**
     * 结束任务
     */
    @Override
    public void testFinishTask() {
        processEngine.getTaskService()
                .complete("30002");
    }

    /**
     * 查看已经完成的任务和当前在执行的任务
     */
    @Override
    public void findHistoryTask() {
        //如果只想获取到已经执行完成的，那么就要加入completed这个过滤条件
        List<HistoricTaskInstance> historicTaskInstances1 = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .taskDeleteReason("completed")
                .list();
        //如果只想获取到已经执行完成的，那么就要加入completed这个过滤条件
        List<HistoricTaskInstance> historicTaskInstances2 = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .list();
        System.out.println("执行完成的任务：" + historicTaskInstances1.size());
        System.out.println("所有的总任务数（执行完和当前未执行完）：" + historicTaskInstances2.size());
    }

    /**
     * 删除已经部署的流程
     */
    @Override
    public void testDelete() {
        //得到流程引擎
        //第一个参数是部署的流程的ID，第二个true表示是进行级联删除
        processEngine.getRepositoryService()
                .deleteDeployment("601", true);
    }

    /**
     * 根据名称查询流程部署
     */
    @Override
    public void testQueryDeploymentByName(String deployName) {
        List<Deployment> deployments = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
//                .deploymentName("请假流程")
                .list();
        for (Deployment deployment : deployments) {
            System.out.println(deployment.getId());
        }
    }

    /**
     * 查询所有的部署流程
     */
    @Override
    public void queryAllDeplyoment() {
        //得到流程引擎
        List<Deployment> lists = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
                .list();
        for (Deployment deployment : lists) {
            System.out.println(deployment.getId() + "    部署名称" + deployment.getName());
        }
    }

    /**
     * 查看流程图
     *
     * @throws Exception
     */
    @Override
    public void testShowImage() throws Exception {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                /**
                 * deploymentID
                 * 文件的名称和路径
                 */
                .getResourceAsStream("22503", "EnglishTesk.myProcess_1.png");
        OutputStream outputStream3 = new FileOutputStream("e:/processimg.png");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream3.write(b);
        }
        inputStream.close();
        outputStream3.close();
    }

    /**
     * 根据pdid查看图片(在act_re_procdef数据表中)
     * 当流程部署之后
     *
     * @throws Exception
     */
    public void testShowImageBypdId() throws Exception {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                .getProcessDiagram("shenqing:1:804");
        OutputStream outputStream = new FileOutputStream("e:/processimg.png");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 查看bpmn文件(在act_re_procdef数据表中)
     *
     * @throws Exception
     */
    public void testShowBpmn() throws Exception {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                .getProcessModel("shenqing:1:804");
        OutputStream outputStream = new FileOutputStream("e:/processimg.bpmn");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 根据piid得到当前正在执行的流程实例的正在活动的节点
     */
    public void testActivity() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        /**
         * 根据piid得到流程实例
         */
        ProcessInstance pi = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId("1001")
                .singleResult();
        //当前流程实例正在执行的activityId
        String activityId = pi.getActivityId();
        System.out.println(activityId);
    }
}
