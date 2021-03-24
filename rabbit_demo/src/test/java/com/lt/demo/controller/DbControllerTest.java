package com.lt.demo.controller;


import com.lt.demo.RabbitApplication;
import com.lt.demo.service.DbSerice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tong.luo
 * @description DbControllerTest
 * @date 2021/1/22 16:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbControllerTest {

    @Autowired
    private DbSerice dbSerice;

    @Test
    public void test1() {
        this.dbSerice.testInsert();
    }
}