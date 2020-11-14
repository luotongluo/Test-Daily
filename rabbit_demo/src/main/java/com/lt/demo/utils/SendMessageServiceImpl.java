package com.lt.demo.utils;

import com.lt.demo.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:35
 * @Description:
 * @Version 1.0
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {
    Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object message) {
//设置回调对象
//        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Constants.SAVE_USER_EXCHANGE_NAME, Constants.SAVE_USER_QUEUE_ROUTE_KEY, message, correlationData);
        logger.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        logger.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (isSendSuccess) {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + s);
        }
    }
}
