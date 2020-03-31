package com.example.webtest;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WebTestApplication {
	private static Logger logger = LoggerFactory.getLogger(WebTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebTestApplication.class, args);
    }

}
