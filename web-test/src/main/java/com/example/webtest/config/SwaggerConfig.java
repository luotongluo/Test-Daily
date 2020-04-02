package com.example.webtest.config;

import io.swagger.annotations.Api;
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
 * @author luotong
 * @description SwaggerConfig
 * 通过注解@EnableSwagger2开启swagger2，apiInfo是接口文档的基本说明信息，包括标题、描述、服务网址、联系人、版本等信息；
 * <p>
 * 在Docket创建中，通过groupName进行分组，paths属性进行过滤，apis属性可以设置扫描包，或者通过注解的方式标识；
 * <p>
 * 通过enable属性，可以在application-{profile}.properties文件中设置相应值，主要用于控制生产环境不生成接口文档。
 * @date 2020/3/27 17:52
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.webtest")) //指定提供接口所在的基包
                .build();
    }

    @Bean("TestApis")
    public Docket testApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/test.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo api 说明")
                .contact(new Contact("test", "null", "email"))
                .description("提供")
                .termsOfServiceUrl("localhost:1111/test/")
                .version("1.0")
                .build();
    }
}