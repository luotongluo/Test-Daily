package com.lt.activit.activiti.service;

import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/12/3 21:18
 * @Description:
 * @Version 1.0
 */
public interface TestService {
    /**
     * 部署流程
     */
    public Map activiti();


    /**
     * 启动流程
     *
     * @param bizId 业务id
     */
    public Map startProcesses(String bizId);

    /**
     * 通过查询 key和code 单号和类型判断有没有提交过审批
     * 没有提交的时候需要提交审批
     *
     * @return
     */
    public Map startProcessesByKey(HashMap<Object, Object> reqmap) throws Exception;

    public void createReviewProcess();

    public void findTasksByUserId();

    /**
     * 根据定义的人来启动流程
     */
    public void startApproveByAssige();

    /**
     * 查询流程定义列表
     */
    public void getQueryList();

    /**
     * 查询个人任务列表
     */
    public void getSomeOnejobs();

    /**
     * 办理任务
     * //办理任务(主要操作ACT_RU_EXECUTION、ACT_RU_TASK表)
     */
    public void doSomeOnejobs();

    /**
     * 历史记录
     */
    public void historyData();

    public void doApproveByAllStep();

    /**
     * 删除部署信息
     */
    public void delApproveInfo();

    /**
     * 删除流程定义(通过删除部署信息达到删除流程定义的目的)
     */
    public void delApproveDely();

    /**
     * 查询部署列表
     */
    public void getDeployList();

}
