package com.lt.demo.service.impl;

import com.lt.demo.bean.UserEntity;
import com.lt.demo.service.UserService;
import com.lt.demo.utils.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:55
 * @Description:
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SendMessageService sendMessageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(UserEntity userEntity) {
        //保存用户操作
        //这里写保存数据库操作...

        //发送消息到RabbitMQ
        sendMessageService.sendMessage(userEntity.getName());
        return userEntity.getId();
    }
}
