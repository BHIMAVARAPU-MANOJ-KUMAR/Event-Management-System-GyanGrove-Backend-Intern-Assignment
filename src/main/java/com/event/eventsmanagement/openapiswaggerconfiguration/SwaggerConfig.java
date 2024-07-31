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
		return GroupedOpenApi.builder()
				.group("public-Events Finder API")
				.pathsToMatch("/api/v1/events/find")
				.pathsToExclude("/api/v1/events/event",
						"/api/v1/events/events",
						"/api/v1/import/csv",
						"/api/v1/export/csv")
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("Events Finder API")
							.description("This is the public API for accessing Event Information")
							.version("v2.6.0")
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
		return GroupedOpenApi.builder()
				.group("admin-File Resource APIs")
				.pathsToMatch("/api/v1/import/csv",
						"/api/v1/export/csv")
				.pathsToExclude("/api/v1/events/find",
						"/api/v1/events/event",
						"/api/v1/events/events")
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("File Resource Import Export APIs")
							.description("This is the Admin API for Import & Export Data Operations")
							.version("v2.6.0")
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
		return GroupedOpenApi.builder()
				.group("admin-Event Creation APIs")
				.pathsToMatch("/api/v1/events/event",
						"/api/v1/events/events")
				.pathsToExclude("/api/v1/events/find",
						"/api/v1/import/csv",
						"/api/v1/export/csv")
				.addOpenApiCustomizer(openAPIs -> {
					openAPIs.info(new Info()
							.title("Event Creation APIs")
							.description("This is the Admin API for Creation of New Events")
							.version("v2.6.0")
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