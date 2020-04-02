package com.lt.cloudtest.service.impl;

import com.lt.cloudtest.CloudTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tong.luo on 2019/12/21 21:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={CloudTestApplication.class,RedisTestServiceImplTest.class})
public class RedisTestServiceImplTest {
    @Autowired
    RedisTestServiceImpl redisTestService;
    @Test
    public void test1(){
        this.redisTestService.JedisTest();
    }
}