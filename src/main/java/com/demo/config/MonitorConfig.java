package com.demo.config;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author james
 * @date 2018/11/14
 */
@Configuration
public class MonitorConfig {

    @Bean
    @Order(Integer.MAX_VALUE - 1)
    public FilterRegistrationBean monitoringFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MonitoringFilter());
        registration.addUrlPatterns("/*");
        registration.setName("monitoring");
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SessionListener> slrBean = new ServletListenerRegistrationBean<>();
        slrBean.setListener(new SessionListener());
        return slrBean;
    }

}
