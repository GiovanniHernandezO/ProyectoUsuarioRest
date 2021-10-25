package com.ejemplo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean filter() {
        final FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new FilterValidation());
        filter.addUrlPatterns("*");
        filter.setEnabled(Boolean.TRUE);
        filter.setAsyncSupported(Boolean.TRUE);
        return filter;
    }
}
