package com.lt.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.demo.config.DemoMqConfig;
import com.lt.demo.config.MqConstants;
import com.lt.demo.service.DemoService;
import com.sun.security.ntlm.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author tong.luo
 * @description DemoServiceImpl
 * @date 2021/1/22 13:48
 */
@Service("DemoService")
public class DemoServiceImpl implements DemoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void directDemo() {
        HashMap<String, Object> dateMap = new HashMap<>(16);
        dateMap.put("time", LocalDateTime.now());
        dateMap.put("data", "directDemo");
        //因为在demo中的路由间配置的字段不同
        this.rabbitTemplate.convertAndSend(DemoMqConfig.DIRECT_QUEUE_EXCHANGE,DemoMqConfig.DIRECT_ROUTE_KEY1,JSON.toJSONString(dateMap));
        LOGGER.info("发送消息使用的交换机为：{}，路由间为：{}，msg：{},before:{}", DemoMqConfig.DIRECT_QUEUE_EXCHANGE,
                DemoMqConfig.DIRECT_ROUTE_KEY1, JSON.toJSONString(dateMap));
    }

    @Override
    public void topicDemo() {
        HashMap<String, Object> dateMap = new HashMap<>(16);
        dateMap.put("time", LocalDateTime.now());
        dateMap.put("data", "topicDemo");
        this.rabbitTemplate.convertAndSend(DemoMqConfig.TOPIC_QUEUE_EXCHANGE,DemoMqConfig.TOPIC_ROUTE_KEY,JSON.toJSONString(dateMap));
        LOGGER.info("发送消息使用的交换机为：{}，路由间为：{}，msg：{},before:{}", DemoMqConfig.TOPIC_QUEUE_EXCHANGE,
                DemoMqConfig.TOPIC_ROUTE_KEY, JSON.toJSONString(dateMap));
    }

    @Override
    public void fanoutDemo() {
        HashMap<String, Object> dateMap = new HashMap<>(16);
        dateMap.put("time", LocalDateTime.now());
        dateMap.put("data", "fanoutDemo");
        this.rabbitTemplate.convertAndSend(DemoMqConfig.FANOUT_QUEUE_EXCHANGE,DemoMqConfig.FANOUT_ROUTE_KEY,JSON.toJSONString(dateMap));
        LOGGER.info("发送消息使用的交换机为：{}，路由间为：{}，msg：{},before:{}", DemoMqConfig.FANOUT_QUEUE_EXCHANGE,
                DemoMqConfig.FANOUT_ROUTE_KEY, JSON.toJSONString(dateMap));
    }
}
