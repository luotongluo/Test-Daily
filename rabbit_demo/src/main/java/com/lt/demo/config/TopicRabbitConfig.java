package com.lt.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LT
 * @Date: 2019/11/5 17:54
 * @Description:
 * @Version 1.0
 */
@Configuration
public class TopicRabbitConfig {

    final static String MESSAGE = "q_topic_message";
    final static String MESSAGES = "q_topic_messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(MqConstants.TOPOIC_QUEUE_NAME);
    }


    /**
     * 声明一个Topic类型的交换机
     *
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MqConstants.TOPOIC_EXCHANGE_NAME);
    }

    /**
     * 绑定Q到交换机,并且指定routingKey
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(MqConstants.TOPOIC_ROUTE_KEY);
    }

//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("com.lt.topic.#");
//    }
}
