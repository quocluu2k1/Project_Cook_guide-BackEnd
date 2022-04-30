//package com.project.cookguide.Cook.guide.project.common;
//
//import org.aopalliance.intercept.Interceptor;
//import org.hibernate.proxy.ProxyConfiguration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.request.WebRequestInterceptor;
//
//@Component
//public class FeignClientInterceptor implements RequestHeader {
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    public static String getBearerTokenHeader() {
//        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
//    }
//
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//
//        requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());
//
//    }
//}
