package com.lt.demo.service.impl;

import com.lt.demo.service.WarnService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tong.luo
 * @description WarnServiceImpl
 * @date 2021/1/21 17:54
 */
@Service
public class WarnServiceImpl implements WarnService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
}
