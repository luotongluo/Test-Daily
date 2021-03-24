package com.lt.demo.recive;

import com.alibaba.fastjson.JSON;
import com.lt.demo.config.MqConstants;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:57
 * @Description:
 * @Version 1.0
 */
@Component
public class ReceiveMessage {
    private static Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);

    @RabbitHandler
    @RabbitListener(queues = MqConstants.SAVE_USER_QUEUE_NAME)
    public void receiveMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                               Channel channel) {
        logger.info("消息接收成功，用户名为：" + message);
        logger.info("deliveryTag:{}", deliveryTag);
        logger.info("channel:{}", JSON.toJSONString(channel));
        //可以添加自定义业务逻辑处理
        try {
            channel.basicAck(deliveryTag, true);
            logger.info("deliveryTag:{}", deliveryTag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = MqConstants.TOPOIC_QUEUE_NAME)
    public void receiveMessage1(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                Channel channel) {
        logger.info("消息接收成功，为：{},after:{}", message);
        logger.info("channel:{}", JSON.toJSONString(channel));
        //可以添加自定义业务逻辑处理
        try {
            channel.basicAck(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
