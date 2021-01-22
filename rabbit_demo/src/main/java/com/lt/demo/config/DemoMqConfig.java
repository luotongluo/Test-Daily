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
 * @author tong.luo
 * @description DemoMqConfig
 * @date 2021/1/22 11:39
 */
@Configuration
public class DemoMqConfig {
    public static final String FANOUT1_QUEUE = "fanout1demo";
    public static final String FANOUT2_QUEUE = "fanout2demo";
    public static final String FANOUT_QUEUE_EXCHANGE = "fanoutExchangedemo";
    public static final String FANOUT_ROUTE_KEY = "fanout.route.key";

    public static final String DIRECT1_QUEUE = "directQueue1demo";
    public static final String DIRECT2_QUEUE = "directQueue2demo";
    public static final String DIRECT_QUEUE_EXCHANGE = "directExchangedemo";
    public static final String DIRECT_ROUTE_KEY1 = "direct.route.key1";
    public static final String DIRECT_ROUTE_KEY2 = "direct.route.key2";

    public static final String TOPIC1_QUEUE = "topicQueue1demo";
    public static final String TOPIC2_QUEUE = "topicQueue2demo";
    public static final String TOPIC_QUEUE_EXCHANGE = "topicExchangeemo";
    public static final String TOPIC_ROUTE_KEY = "sms.mail.test";

    @Bean
    public Queue fanout1() {
        return new Queue(FANOUT1_QUEUE);
    }

    @Bean
    public Queue fanout2() {
        return new Queue(FANOUT2_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchangeDemo() {
        // 三个构造参数：name durable autoDelete
        return new FanoutExchange(FANOUT_QUEUE_EXCHANGE, false, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(fanout1()).to(fanoutExchangeDemo());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(fanout2()).to(fanoutExchangeDemo());
    }

    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT1_QUEUE);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT2_QUEUE);
    }

    @Bean
    public DirectExchange directExchangeDemo() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange(DIRECT_QUEUE_EXCHANGE, false, false);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchangeDemo()).with(DIRECT_ROUTE_KEY1);
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchangeDemo()).with(DIRECT_ROUTE_KEY2);
    }

    /**
     *     主题交换机示例
      */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC1_QUEUE);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC2_QUEUE);
    }

    @Bean
    public TopicExchange topicExchangeDemo() {
        // 三个构造参数：name durable autoDelete
        return new TopicExchange(TOPIC_QUEUE_EXCHANGE, false, false);
    }

    /**
     * *：能够模糊匹配一个单词。
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchangeDemo()).with("sms.*");
    }

    /**
     * #：能够模糊匹配零个或多个单词。
     * @return
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchangeDemo()).with("*.mail.#");
    }


}
