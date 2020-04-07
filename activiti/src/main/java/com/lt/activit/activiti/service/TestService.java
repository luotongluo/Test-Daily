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
     * 启动流程
     *
     * @param bizId 业务id
     */
    public void startProcesses(String bizId);

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

}
