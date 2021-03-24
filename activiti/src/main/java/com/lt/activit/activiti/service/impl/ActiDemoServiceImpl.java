package com.lt.activit.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.activit.activiti.service.ActiDemoService;
import com.lt.activit.activiti.vo.ActiReqVo;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tong.luo
 * @description ActiDemoServiceImpl
 * @date 2021/3/23 15:42
 */
@Service
public class ActiDemoServiceImpl implements ActiDemoService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    /**
     * @param actiReqVo ("progress/TestBmpn.bpmn")
     * @return
     */
    @Override
    public Map createDemoActi(ActiReqVo actiReqVo) {
        String resourcePath = actiReqVo.getResourcePath();
        Deployment deploy = this.repositoryService.createDeployment()
                .key(actiReqVo.getBusinessKey())
                .addClasspathResource(resourcePath).deploy();
        LOGGER.info("deploy ：{}", JSON.toJSONString(deploy));
        return null;
    }

    /**
     * @param actiReqVo
     * @return
     */
    @Override
    public Map startDemoActi(ActiReqVo actiReqVo) {
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(actiReqVo.getBusinessKey());
        this.LOGGER.info("启动流程返回值为：{}",JSON.toJSONString(processInstance));
        return null;
    }
}
