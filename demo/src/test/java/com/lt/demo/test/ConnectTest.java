//package com.lt.demo.test;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.QueueingConsumer;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.Date;
//
///**
// * @Author: LT
// * @Date: 2019/4/15 10:45
// * @Description:
// * @Version 1.0
// */
//public class ConnectTest {
//
//    private final static String QUEUE_NAME = "test";
//    private final static String userName = "admin";
//    private final static String password = "admin";
//    private final static String virtualHost = "/";
//    private final static int portNumber = 5672;
//    private final static String hostName = "master";
//    private final static String host = "192.168.75.134";
//
//    @Test
//    public void connectTest() throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername(userName);
//        factory.setPassword(password);
//        factory.setHost(host);
//        factory.setPort(portNumber);
//        Connection connection = null;
//        try {
//            connection = factory.newConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Channel channel = null;
//        try {
//            channel = connection.createChannel();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//        Date nowTime = new Date();
//
//        while (true) {
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("RecieveTime: " + nowTime);
//            System.out.println(" [x] Received '" + message + "'");
//        }
//    }
//}
