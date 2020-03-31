package com.example.webtest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author luotong
 * @description DruidConfig
 * 配置druid需要的配置类，引入application.properties文件中以spring.datasource开头的信息
 * 因此需要在application.properties文件中配置相关信息。
 * @date 2020/3/31 14:02
 */
@Configuration
//@Component
public class DruidConfig {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
    //配置druid监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap<String, String> map = new HashMap<>();
        map.put("loginUsername","admin");
        map.put("loginPassword","123456");
        bean.setInitParameters(map);

        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        HashMap<String, String> map = new HashMap<>();
        map.put("exclusions","*.js");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
