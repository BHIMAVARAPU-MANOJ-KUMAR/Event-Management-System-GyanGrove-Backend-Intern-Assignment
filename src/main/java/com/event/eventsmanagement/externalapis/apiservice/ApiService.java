package com.event.eventsmanagement.externalapis.apiservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "ApiService")
public final class ApiService {
	
	@Value("${weather.api.key}")
	private String weatherApiKey;
	
	@Value("${distance.api.key}")
	private String distanceApiKey;

	public String getWeatherApiKey() {
		return weatherApiKey;
	}

	public String getDistanceApiKey() {
		return distanceApiKey;
	}
}