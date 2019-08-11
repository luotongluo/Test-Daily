package com.lt.demo.recive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
    @RabbitListener(queues = "user.save.queue.name")
    public void receiveMessage(String userName) {
        logger.info("消息接收成功，用户名为：" + userName);
        //可以添加自定义业务逻辑处理
    }
}
