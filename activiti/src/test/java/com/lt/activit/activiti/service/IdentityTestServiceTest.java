package com.lt.activit.activiti.service;

import com.lt.activit.activiti.ActivitiApplication;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luotong
 * @description IdentityTestServiceTest
 * @date 2020/4/10 16:53
 */
@SpringBootTest/*(classes = {ActivitiApplication.class, IdentityTestServiceTest.class})*/
public class IdentityTestServiceTest {
    @Autowired
    private IdentityTestService identityTestService;
    @Autowired
    private IdentityService identityService;

    @Test
    public void addUser() {
        this.identityTestService.adduser();
    }
    @Test
    public void getUser() {
        User user = identityService.newUser("null");
        user.setId("123");
        user.setEmail("123@email.com");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setPassword("setPassword");
        this.identityTestService.getUser(user);
    }

}