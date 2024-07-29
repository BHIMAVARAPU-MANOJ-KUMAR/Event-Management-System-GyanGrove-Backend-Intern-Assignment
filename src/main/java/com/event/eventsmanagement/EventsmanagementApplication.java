package com.event.eventsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.event.eventsmanagement.filter.CustomHeaderFilter;

@SpringBootApplication
public class EventsmanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventsmanagementApplication.class, args);
	}
	
	FilterRegistrationBean<CustomHeaderFilter> customHeaderFilter() {
        FilterRegistrationBean<CustomHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomHeaderFilter());
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }
}