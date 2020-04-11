package com.lt.activit.activiti.utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/12/6 17:28
 * @Description:
 * @Version 1.0
 */
public class Utils {
    @Autowired
    private static ProcessEngine processEngine;

    /**
     * 当前用户-->当前用户正在执行的任务--->当前正在执行的任务的piid-->该任务所在的流程实例
     *
     * @param assignee
     * @return
     */
    public static List<ProcessInstance> getPIByUser(String assignee) {
        List<ProcessInstance> pis = new ArrayList<ProcessInstance>();
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        /**
         * 该用户正在执行的任务
         */
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        for (Task task : tasks) {
            /**
             * 根据task-->piid-->pi
             */
            String piid = task.getProcessInstanceId();
            ProcessInstance pi = processEngine.getRuntimeService()
                    .createProcessInstanceQuery()
                    .processInstanceId(piid)
                    .singleResult();
            pis.add(pi);
        }
        return pis;
    }

    /**
     * 根据当前的登录人能够推导出所在的流程定义
     */
    public static void getProcessInstance(String assignee) {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        for (Task task : tasks) {
            String pdid = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = processEngine.getRepositoryService()
                    .createProcessDefinitionQuery()
                    .processDefinitionId(pdid)
                    .singleResult();
        }
    }
}
