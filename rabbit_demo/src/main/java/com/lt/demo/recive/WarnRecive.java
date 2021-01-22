package com.lt.demo.recive;

import com.lt.demo.config.MqConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author tong.luo
 * @description WarnRecive
 * @date 2021/1/21 18:42
 */
@Component
public class WarnRecive {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarnRecive.class);

    @RabbitHandler
    @RabbitListener(queues = MqConstants.WARN_QUEUE)
    public void test1(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                      Channel channel) throws Exception{
        LOGGER.info("req:{}", message);
        channel.basicAck(deliveryTag,true);
    }
}
