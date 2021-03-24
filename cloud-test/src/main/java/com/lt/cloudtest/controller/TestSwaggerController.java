package com.lt.cloudtest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author: LT
 * @Date: 2020/2/17 16:42
 * @Description: Swagger使用的注解及其说明：
 * @Api：用在类上，说明该类的作用。
 * @ApiOperation：注解来给API增加方法说明。
 * @ApiImplicitParams : 用在方法上包含一组参数说明。
 * @ApiImplicitParam：用来注解来给方法入参增加说明。
 * @ApiResponses：用于表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 l code：数字，例如400
 * l message：信息，例如"请求参数没填好"
 * l response：抛出异常的类
 * @ApiModel：描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 * @ApiModelProperty：描述一个model的属性
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
@Api(tags = "API文档", value = "/", description = "这是我全部的Get方法")
public class TestSwaggerController {
    @GetMapping(value = "/getCookies")
    @ApiOperation(value = "通过这个方法可以获取到Cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜zuozewei获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @GetMapping(value = "/get/with/cookies")
    @ApiOperation(value = "通过这个方法可以获取到Cookies", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "这是一个需要带着cookies信息才能访问的get请求";
            }
        }

        return "必须携带cookies信息来";
    }
}
