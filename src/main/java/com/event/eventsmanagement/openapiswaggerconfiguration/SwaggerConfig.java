package com.event.eventsmanagement.openapiswaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration(value = "Swagger Configuration")
public class SwaggerConfig {
	
	@Bean(name = "PublicAPI")
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("public")
				.pathsToMatch("/api/v1/events/find")
				.pathsToExclude("/api/v1/events/event",
						"/api/v1/events/events",
						"/api/v1/import/csv",
						"/api/v1/export/csv")
				.build();
	}
	
	@Bean(name = "AdminAPI")
	GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder()
				.group("admin")
				.pathsToMatch("/api/v1/events/event",
						"/api/v1/events/events",
						"/api/v1/import/csv",
						"/api/v1/export/csv")
				.pathsToExclude("/api/v1/events/find")
				.build();
	}
	
	@Bean(name = "customOpenAPI")
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Events Management API")
						.description("APIs for Managing Events"));
	}
}