package com.lt.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.demo.bean.UserEntity;
import com.lt.demo.config.MqConstants;
import com.lt.demo.service.UserService;
import com.lt.demo.utils.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:55
 * @Description:
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(UserEntity userEntity) {
        //保存用户操作
        //这里写保存数据库操作...

        //发送消息到RabbitMQ
        sendMessageService.sendMessage(userEntity.getName());
        return userEntity.getId();
    }

    /**
     * 测试fanout的mq
     */
    @Override
    public void testFanout() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(16);
        stringObjectHashMap.put("test", 123);
        stringObjectHashMap.put("ok", "123123");
        stringObjectHashMap.put("user_id", "123user_id123");
        stringObjectHashMap.put("image_path", "path:1/23/3213");
        this.rabbitTemplate.convertAndSend(MqConstants.FANOUT_EXCAHNGE_NAME, MqConstants.FANOUT_EXCAHNGE_NAME, JSON.toJSONString(stringObjectHashMap));
    }

    /**
     * ttl msg
     *
     * @param message
     */
    @Override
    public void sendTtl(Object message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        HashMap<String, Object> msgMap = new HashMap<>(16);
        msgMap.put("time", localDateTime);
        msgMap.put("date", "ttltest");
        //设置过期时间3s
        this.rabbitTemplate.convertAndSend(MqConstants.TOPOIC_EXCHANGE_NAME, MqConstants.TOPOIC_ROUTE_KEY, JSON.toJSONString(msgMap));
        LOGGER.info("发送消息使用的交换机为：{}，路由间为：{}，msg：{},before:{}", MqConstants.TOPOIC_EXCHANGE_NAME,
                 MqConstants.TOPOIC_ROUTE_KEY, JSON.toJSONBytes(msgMap),JSON.toJSONString(msgMap));
    }
}
