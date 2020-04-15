package com.lt.activit.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author tong.luo
 * @description ActiConfig
 * 使用接口注入的方式
 * 耦合性比較强
 * @date 2020/4/11 20:55
 */
@Component
public class ActiConfig implements ProcessEngineConfigurationConfigurer {
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setMailServerHost("localhost")
                .setMailServerPort(25);

    }
}
