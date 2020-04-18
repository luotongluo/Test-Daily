package com.lt.activit.activiti.controller;

import com.lt.activit.activiti.enums.DefinitionKeyEnum;
import com.lt.activit.activiti.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/12/3 22:02
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    /**
     * 部署流程
     *
     * @return
     */
    @RequestMapping("activiti")
    public Map activiti() {
        return this.testService.activiti();
    }

    /**
     * 启动流程
     *
     * @return
     */
    @RequestMapping("startProcesses")
    public Map startProcesses() {
        return this.testService.startProcesses(null);
    }

    /**
     * 删除部署信息
     *
     * @return
     */
    @RequestMapping("delApproveInfo")
    public Map delApproveInfo() {
        this.testService.delApproveInfo();
        return null;
    }

    /**
     * 启动流程
     *
     * @return
     */
    @RequestMapping("startProcessesByKey")
    public Map startProcessesByKey() throws Exception {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("definitionKey", DefinitionKeyEnum.TESTBMPN.getName());
        hashMap.put("bussnessCode", "definitionKey");
        hashMap.put("oprator", "oprator");
        HashMap<String, Object> variablesmap = new HashMap<>();
        variablesmap.put("oneLevel",123);
        hashMap.put("variables", variablesmap);
        return this.testService.startProcessesByKey(hashMap);
    }
}
