package com.lt.demo.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:33
 * @Description:
 * @Version 1.0
 */
public interface SendMessageService extends RabbitTemplate.ConfirmCallback {
    /**
     * 发送消息方法
     *
     * @param message 发送内容
     */
    void sendMessage(Object message);
}
