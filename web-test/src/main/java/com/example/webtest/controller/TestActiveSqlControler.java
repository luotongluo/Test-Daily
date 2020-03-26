package com.example.webtest.controller;

import com.example.webtest.service.TestSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author luotong
 * @description TestActiveSqlControler
 * 测试动态sql
 * @date 2020/3/26 19:28
 */
@Controller
public class TestActiveSqlControler {
    private static Logger logger = LoggerFactory.getLogger(TestActiveSqlControler.class);
    @Autowired
    private TestSqlService testSqlService;
}
