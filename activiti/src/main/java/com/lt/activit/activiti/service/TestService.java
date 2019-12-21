package com.lt.activit.activiti.service;

import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/12/3 21:18
 * @Description:
 * @Version 1.0
 */
public interface TestService {
    /**
     *
     */
    public void activiti();

    /**
     * @return
     */
    public List<String> user();

    /**
     * 启动流程
     *
     * @param bizId 业务id
     */
    public void startProcesses(String bizId);

    /**
     * <p>描述: 根据用户id查询待办任务列表</p>
     *
     * @author 范相如
     * @date 2018年2月25日
     */
    public List<Task> findTasksByUserId(String userId);

    public void start();

    /**
     * 发布一个新的审批流
     */
    public void creatActivitiTask(String fileName, String nameOfApprove);

    /**
     * 2：启动流程实例
     */
    public void testStartProcessInstance();

    /**
     * 完成请假申请
     */
    public void testQingjia();

    /**
     * 查询正在执行的任务
     */
    public void testQueryTask(String assingee);

    /**
     * 完成任务
     */
    public void testFinishTaskManager();

    /**
     * 结束任务
     */
    public void testFinishTask();

    /**
     * 查看已经完成的任务和当前在执行的任务
     */
    public void findHistoryTask();

    /**
     * 删除已经部署的流程
     */
    public void testDelete();

    /**
     * 根据名称查询流程部署
     */
    public void testQueryDeploymentByName(String deployName);

    /**
     * 查询所有的部署流程
     */
    public void queryAllDeplyoment();

    /**
     * 查看流程图
     *
     * @throws Exception
     */
    public void testShowImage() throws Exception;
}
