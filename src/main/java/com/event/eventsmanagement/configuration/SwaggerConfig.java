package com.event.eventsmanagement.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration(value = "Swagger Configuration")
public class SwaggerConfig {
	
	@Bean(name = "publicAPI")
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("public-apis")
				.pathsToMatch("/**")
				.build();
	}
	
	@Bean(name = "customOpenAPI")
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Events Management API")
						.version("0.0.1"));
	}
}