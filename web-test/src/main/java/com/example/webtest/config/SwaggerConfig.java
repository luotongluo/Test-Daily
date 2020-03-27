package com.example.webtest.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author luotong
 * @description SwaggerConfig
 * 通过注解@EnableSwagger2开启swagger2，apiInfo是接口文档的基本说明信息，包括标题、描述、服务网址、联系人、版本等信息；
 *
 * 在Docket创建中，通过groupName进行分组，paths属性进行过滤，apis属性可以设置扫描包，或者通过注解的方式标识；
 *
 * 通过enable属性，可以在application-{profile}.properties文件中设置相应值，主要用于控制生产环境不生成接口文档。
 * @date 2020/3/27 17:52
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean("UserApis")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/user.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    @Bean("CustomApis")
    public Docket customApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("客户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/custom.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XXXXX系统平台接口文档")
                .description("提供子模块1/子模块2/子模块3的文档, 更多请关注公众号: 随行享阅")
                .termsOfServiceUrl("https://xingtian.github.io/trace.github.io/")
                .version("1.0")
                .build();
    }
}