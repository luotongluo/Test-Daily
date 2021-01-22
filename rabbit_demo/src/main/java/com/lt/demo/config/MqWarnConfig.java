package com.lt.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tong.luo
 * @description MqWarnConfig
 * @date 2021/1/21 18:17
 */
@Configuration
public class MqWarnConfig {
//    @Bean
//    public Queue warnQueueMessage() {
//        return new Queue(MqConstants.WARN_QUEUE);
//    }
//
//    @Bean
//    FanoutExchange warnQexchange() {
//        return new FanoutExchange(MqConstants.WARN_QUEUE_EXCNAHGE);
//    }

//    @Bean
//    Binding bindingWarnQExchangeMessage(Queue warnQueueMessage, FanoutExchange warnQexchange) {
//        return BindingBuilder.bind(warnQueueMessage).to(warnQexchange);
////        return BindingBuilder.bind(warnQueueMessage).to(warnQexchange).with(MqConstants.WARN_QUEUE_ROUTE_KEY);
//
//    }

    /**
     * 如果 warn* 表示后面没有小数点
     * 而 有小数点的*  则表示的为  小数点后面存在数据
     * @param warnQueueMessage
     * @param warnQexchange
     * @return
     */
//    @Bean
//    Binding bindingWarn1QExchangeMessage(Queue warnQueueMessage, FanoutExchange warnQexchange) {
//        return BindingBuilder.bind(warnQueueMessage).to(warnQexchange);
//    }
//
//    @Bean
//    Binding bindingWarn2QExchangeMessage(Queue warnQueueMessage, FanoutExchange warnQexchange) {
//        return BindingBuilder.bind(warnQueueMessage).to(warnQexchange)/*.with("*.critical.*")*/;
//    }
}
