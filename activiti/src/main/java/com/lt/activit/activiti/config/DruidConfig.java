package com.lt.activit.activiti.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
@MapperScan(basePackages = {"com.lt.activit.activiti.dao"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class DruidConfig {
//        @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties newDataSourceProperties() {
//        return new DataSourceProperties();
//    }

//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource newDataSource() {
//        return newDataSourceProperties().initializeDataSourceBuilder().build();
//    }

    @Bean
    public SqlSessionFactory newSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));

            // MyBatis 自动映射驼峰命名
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            bean.setConfiguration(configuration);

            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    @Bean
//    public SqlSessionTemplate newSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
//        return template;
//    }

//    /**
//     * 事务管理
//     *
//     * @return 事务管理实例
//     */
//    @Bean
//    public PlatformTransactionManager platformTransactionManager() {
//        return new DataSourceTransactionManager(newDataSource());
//    }

    //配置druid监控
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap<String, String> map = new HashMap<>();
        map.put("loginUsername", "admin");
        map.put("loginPassword", "123456");
        bean.setInitParameters(map);

        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        HashMap<String, String> map = new HashMap<>();
        map.put("exclusions", "*.js");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
