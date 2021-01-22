package com.lt.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:17
 * @Description:该配置类大致分为三个步骤： a. DirectExchange交换机，为交换机设置一个名称
 * <p>
 * b. Queue队列，为消息队列设置一个名称，注意消息接收者监听的队列名称必须与消息发送者注册的队列名称一致，
 * 否则消息不能分发成功到消息接收者。
 * <p>
 * c. Binding绑定，消息绑定的目的就是将Queue实例绑定到Exchange上，并且通过设置的路由Key进行消息转发，配置了路由Key后，
 * 只有符合该路由配置的消息才会被转发到绑定交换上的消息队列。
 * @Version 1.0
 */
@Configuration
public class SaveUserQueueConfiguration {


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConstants.TOPOIC_EXCHANGE_NAME);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue(MqConstants.TOPOIC_QUEUE_NAME, true);
    }

    @Bean
    public Binding topicBind() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(MqConstants.TOPOIC_ROUTE_KEY);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConstants.FANOUT_EXCAHNGE_NAME);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue(MqConstants.FANOUT_QUEUE_ROUTE_NAME, true);
    }

    @Bean
    public Binding fanoutBind() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(MqConstants.FANOUT_QUEUE_KEY);
    }
}
