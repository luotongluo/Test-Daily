package com.lt.demo.config;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:16
 * @Description:
 * @Version 1.0
 */
public class MqConstants {
    /**
     * 系统警告相关的配置
     */
    public static final String WARN_QUEUE = "warn.queue";
    public static final String WARN_QUEUE_EXCNAHGE = "warn.queue.exchange";
    public static final String WARN_QUEUE_ROUTE_KEY = "warn.critical.rate.limit.key";

    /**
     * 保存用户-交换机名称
     */
    public static final String SAVE_USER_EXCHANGE_NAME = "user.save.direct.exchange.name";
    /**
     * 保存用户-队列名称
     */
    public static final String SAVE_USER_QUEUE_NAME = "user.save.direct.queue.name";
    /**
     * 保存用户-队列路由键
     */
    public static final String SAVE_USER_QUEUE_ROUTE_KEY = "user.save.direct.queue.route.key";

    /**
     * 主题类型交换机名称
     */
    public static final String TOPOIC_EXCHANGE_NAME = "com.lt.topic.exchange.name";
    /**
     * 主体类型队列名称
     */
    public static final String TOPOIC_QUEUE_NAME = "com.lt.topic.queue.name";
    /**
     * 主题队列routekey
     */
    public static final String TOPOIC_ROUTE_KEY = "com.lt.topic.queue.key";
    /**
     * 广播类型的交换机名称
     */
    public static final String FANOUT_EXCAHNGE_NAME = "com.lt.fanout.exchange.name";
    /**
     * 广播类型的队列key
     */
    public static final String FANOUT_QUEUE_KEY = "com.lt.fanout.queue.key";
    /**
     * 广播类型的队列的name
     */
    public static final String FANOUT_QUEUE_ROUTE_NAME = "com.lt.fanout.queue.name";
}
