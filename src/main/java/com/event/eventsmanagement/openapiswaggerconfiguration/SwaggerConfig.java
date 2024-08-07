package com.event.eventsmanagement.openapiswaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration(value = "Swagger Configuration")
public class SwaggerConfig {
	
	@Bean(name = "PublicAPI")
	GroupedOpenApi publicApi() {
		String[] pathsToMatch = {"/api/v1/events/find"};
		String[] packagedToMatch = {"com.event.eventsmanagement.eventscontroller"}; 
		return GroupedOpenApi.builder()
				.group("public-Events Finder API")
				.pathsToMatch(pathsToMatch)
				.packagesToScan(packagedToMatch)
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("Events Finder API")
							.description("This is the public API for accessing Event Information")
							.version("2.6.0")
							.contact(new Contact()
									.name("Manoj Kumar Reddy")
									.email("manojbh1999@gmail.com"))
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.org")));
				})
				.build();
	}
	
	@Bean(name = "AdminAPI1")
	GroupedOpenApi adminApi1() {
		String[] pathsToMatch = {"/api/v1/import/csv"};
		String[] packagedToMatch = {"com.event.eventsmanagement.eventscontroller.eventdatauploadcontroller"};
		return GroupedOpenApi.builder()
				.group("admin-CSV File Upload API")
				.pathsToMatch(pathsToMatch)
				.packagesToScan(packagedToMatch)
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("File Resource Import API")
							.description("This is the Admin API for Import Data Operation")
							.version("2.6.0")
							.contact(new Contact()
									.name("Manoj Kumar Reddy")
									.email("manojbh1999@gmail.com"))
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.org")));
				})
				.build();
	}
	
	@Bean(name = "AdminAPI3")
	GroupedOpenApi adminApi3() {
		String[] pathsToMatch = {"/api/v1/export/csv"};
		String[] packagedToMatch = {"com.event.eventsmanagement.eventscontroller.eventdatadownloadcontroller"};
		return GroupedOpenApi.builder()
				.group("admin-CSV File Download API")
				.pathsToMatch(pathsToMatch)
				.packagesToScan(packagedToMatch)
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("File Resource Export API")
							.description("This is the Admin API for Export Data Operation")
							.version("2.6.0")
							.contact(new Contact()
									.name("Manoj Kumar Reddy")
									.email("manojbh1999@gmail.com"))
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.org")));
				})
				.build();
	}
	
	@Bean(name = "AdminAPI2")
	GroupedOpenApi adminApi2() {
		String[] pathsToMatch = {"/api/v1/events/event", "/api/v1/events/events"};
		String[] packagedToMatch = {"com.event.eventsmanagement.eventscontroller"};
		return GroupedOpenApi.builder()
				.group("admin-Event Creation APIs")
				.pathsToMatch(pathsToMatch)
				.packagesToScan(packagedToMatch)
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("Event Creation APIs")
							.description("This is the Admin API for Creation of New Events")
							.version("2.6.0")
							.contact(new Contact()
									.name("Manoj Kumar Reddy")
									.email("manojbh1999@gmail.com"))
							.license(new License()
									.name("Apache 2.0")
									.url("http://springdoc.org")));
				})
				.build();
	}
	
}