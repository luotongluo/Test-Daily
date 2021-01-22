package com.lt.demo.recive;

import com.lt.demo.config.DemoMqConfig;
import com.lt.demo.config.MqConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author tong.luo
 * @description DemoReceive
 * @date 2021/1/22 13:56
 */
@Component("DemoReceive")
public class DemoReceive {
    private static Logger logger = LoggerFactory.getLogger(DemoReceive.class);

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.DIRECT1_QUEUE)
    public void receiveMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                               Channel channel/*, Message message*/) {
        logger.info("direct1:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.DIRECT2_QUEUE)
    public void receiveMessage2(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                Channel channel) {
        logger.info("direct2:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.FANOUT1_QUEUE)
    public void receivefanMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                  Channel channel) {
        logger.info("fan1:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.FANOUT2_QUEUE)
    public void receivefanMessage2(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                   Channel channel) {
        logger.info("fan2:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.TOPIC1_QUEUE)
    public void receivetopMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                  Channel channel) {
        logger.info("top1:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = DemoMqConfig.TOPIC2_QUEUE)
    public void receivetop2Message2(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                    Channel channel) {
        logger.info("top2:{}", message);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
