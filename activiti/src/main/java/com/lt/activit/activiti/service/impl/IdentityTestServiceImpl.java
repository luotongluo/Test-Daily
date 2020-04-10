package com.lt.activit.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.activit.activiti.service.IdentityTestService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luotong
 * @description IdentityTestServiceImpl
 * @date 2020/4/10 16:52
 */
@Service
public class IdentityTestServiceImpl implements IdentityTestService {
    private static Logger logger = LoggerFactory.getLogger(IdentityTestServiceImpl.class);
    @Resource
    private IdentityService identityService;

    @Override
    public void adduser() {
        User newUser = identityService.newUser("new user");
        newUser.setId("123");
        newUser.setEmail("123@email.com");
        newUser.setFirstName("firstname");
        newUser.setLastName("lastname");
        newUser.setPassword("setPassword");
        newUser.isPictureSet();
        identityService.saveUser(newUser);
        logger.info("newUser:{}", JSON.toJSONString(newUser));
        UserQuery userQuery = identityService.createUserQuery();
        logger.info("userQuery:{}", JSON.toJSONString(userQuery));
    }

    @Override
    public void getUser(User user) {
        UserQuery userQuery = identityService.createUserQuery().orderByUserId();
        UserQuery query = userQuery.userFirstName(user.getFirstName());
        logger.info("userQuery:{}", JSON.toJSONString(userQuery));
        logger.info("query:{}", JSON.toJSONString(query));
    }
}
