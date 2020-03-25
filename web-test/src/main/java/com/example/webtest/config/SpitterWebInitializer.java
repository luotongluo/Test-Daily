//package com.example.webtest.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * @author luotong
// * @description SpitterWebInitializer
// * DispatcherServlet是Spring MVC的核心，其负责将请求路由到其他组件中。可通过将Servlet配置在web.xml
// * 中或者使用Java显示编码方式将DispatcherServlet配置在Servlet容器中，本例中设置的SpittrWebAppInitializer
// * @date 2020/3/25 11:07
// */
//public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[] { RootConfig.class };
//
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[] { WebConfig.class };
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] { "/" };
//
//    }
//}
