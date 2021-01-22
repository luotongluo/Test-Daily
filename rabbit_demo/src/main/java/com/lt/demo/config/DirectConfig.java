package com.lt.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tong.luo
 * @description DirectConfig
 * @date 2021/1/22 10:47
 */
@Configuration
public class DirectConfig {
    /**
     * 配置交换机实例
     *
     * @return
     */
    @Bean("directExchange")
    public DirectExchange directExchange() {
        return new DirectExchange(MqConstants.SAVE_USER_EXCHANGE_NAME);
    }

    /**
     * 配置队列实例，并且设置持久化队列
     *
     * @return
     */
    @Bean("queue")
    public Queue queue() {
        return new Queue(MqConstants.SAVE_USER_QUEUE_NAME);
    }

    /**
     * 将队列绑定到交换机上，并设置消息分发的路由键
     *
     * @return
     */
    @Bean
    public Binding binding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queue()).to(directExchange()).with(MqConstants.SAVE_USER_QUEUE_ROUTE_KEY);
    }
}
