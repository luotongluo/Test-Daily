package com.lt.demo.controller;

import com.lt.demo.bean.UserEntity;
import com.lt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:39
 * @Description:
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/sendMessage")
    public void sendMessage() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(20);
        userEntity.setName("zhangsan");
        userService.saveUser(userEntity);
    }
}
