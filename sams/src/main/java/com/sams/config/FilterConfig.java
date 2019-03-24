package com.sams.config;

import com.sams.filter.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registeAccessFilter(){
        FilterRegistrationBean<AccessFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new AccessFilter());
        return filter;
    }
}
