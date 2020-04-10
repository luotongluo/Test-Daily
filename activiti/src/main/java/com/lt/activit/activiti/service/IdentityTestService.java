package com.lt.activit.activiti.service;

import org.activiti.engine.identity.User;

/**
 * @author luotong
 * @description IdentityTestService
 * @date 2020/4/10 16:52
 */
public interface IdentityTestService {
    /**
     * Creates a new user
     */
    void adduser();

    void getUser(User user);
}
