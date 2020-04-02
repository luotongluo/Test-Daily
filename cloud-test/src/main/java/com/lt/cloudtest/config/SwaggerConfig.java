package com.lt.cloudtest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: LT
 * @Date: 2020/2/17 16:43
 * @Description:主要是添加注解@EnableSwagger2和定义Docket的bean类。
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //是否开启swagger，正式环境一般是需要关闭的，可根据Springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.lt.cloudtest"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot-Swagger2集成和使用示例")
                .description("7DGroup | test")
                // 作者信息
                .contact(new Contact("test", "https://blog.csdn.net/qq_40408317", "1282875540@qq.com"))
                .version("1.0.0")
                .build();
    }
}
