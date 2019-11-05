package com.lt.rabbit.demo.recive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: LT
 * @Date: 2019/11/5 17:54
 * @Description:
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "q_hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
