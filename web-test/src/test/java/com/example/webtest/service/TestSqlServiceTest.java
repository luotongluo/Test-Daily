package com.example.webtest.service;

import com.alibaba.fastjson.JSON;
import com.example.webtest.WebTestApplication;
import com.example.webtest.dao.TestMapper;
import com.example.webtest.po.ElectronicInvoiceShopResVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luotong
 * @description TestSqlServiceTest
 *
 * @date 2020/3/26 14:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WebTestApplication.class, TestSqlServiceTest.class})
public class TestSqlServiceTest {

    @Autowired
    private TestSqlService testSqlService;
    @Autowired
    private TestMapper testMapper;

    @Test
    public void test1() {
        String activeSql = this.testSqlService.testActiveSql(new HashMap());
        System.out.println(activeSql);
//        this.testSqlService.getdate();
//
//        this.testSqlService.insert(new ElectronicInvoiceShopResVo());
//        Integer date = this.testSqlService.getDate();
//        System.out.println(date);
    }

    @Test
    public void testActiveSql() {
        Object list = testMapper.insertDb();
        System.out.println(JSON.toJSON(list));
    }
}