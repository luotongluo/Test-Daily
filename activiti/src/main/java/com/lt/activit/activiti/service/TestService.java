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
}
