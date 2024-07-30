package com.event.eventsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.event.eventsmanagement.filters.CustomHeadersFilter;

@SpringBootApplication
public class EventsmanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventsmanagementApplication.class, args);
	}
	
	FilterRegistrationBean<CustomHeadersFilter> customHeaderFilter() {
        FilterRegistrationBean<CustomHeadersFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomHeadersFilter());
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }
}