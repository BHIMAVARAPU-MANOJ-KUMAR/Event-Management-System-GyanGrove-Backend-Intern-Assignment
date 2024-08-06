package com.event.eventsmanagement.externalapis.apiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "ApiService")
public final class ApiService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiService.class);
	
	@Value("${weather.api.key}")
	private String weatherApiKey;
	
	@Value("${distance.api.key}")
	private String distanceApiKey;

	public String getWeatherApiKey() {
		LOGGER.info("Called getWeatherApiKey method: {}", weatherApiKey);
		return weatherApiKey;
	}

	public String getDistanceApiKey() {
		LOGGER.info("Called getDistanceApiKey method: {}", distanceApiKey);
		return distanceApiKey;
	}
}