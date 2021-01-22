package com.lt.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2019/11/5 17:39
 * @Description:
 * @Version 1.0
 */
@Component
public class MsgSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        System.out.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //发送消息
        Map<String, Object> msg = new HashMap<String, Object>(16);
        msg.put("type", "1");
        msg.put("date", date);
        template.convertAndSend("type2", JSON.toJSONString(msg));
        // 休眠1秒
        Thread.sleep(1000);
        //容器销毁
        ctx.close();
    }
}
