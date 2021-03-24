package com.example.webtest.controller;

import com.example.webtest.service.TestSqlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;

/**
 * @author luotong
 * @description TestActiveSqlControler
 * 测试动态sql
 * @date 2020/3/26 19:28
 */
@Controller
@RequestMapping("test")
@Api(tags = "测试模块")
public class TestActiveSqlControler {
    private static Logger logger = LoggerFactory.getLogger(TestActiveSqlControler.class);
    @Autowired
    private TestSqlService testSqlService;

    @GetMapping("/query/{id}")
    @ApiOperation("通过ID查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
    public String findById(@PathVariable int id) {
        return " findById" + id;
    }

    @ApiOperation("testtest")
    @RequestMapping("testtest")
    public String testtest() {
        logger.info("test");
        return "123";
    }
}
