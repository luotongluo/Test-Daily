package com.example.webtest.controller;

import com.example.webtest.service.TestSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luotong
 * @description HomeController
 * @date 2020/3/25 11:27
 */
@RestController
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private TestSqlService testSqlService;

    @RequestMapping(value = "/")
    public String home() {
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "home";
    }

    @RequestMapping("getSqlRet")
    public String getSqlRet() {
       return this.testSqlService.testActiveSql(new HashMap());
    }

    @ResponseBody
    @RequestMapping(value = "/login"/*,method = RequestMethod.POST*/)
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
//            User user =new User(userName,password);
            request.getSession().setAttribute("user","user");
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

}
