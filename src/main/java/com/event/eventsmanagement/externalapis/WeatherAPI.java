package com.event.eventsmanagement.externalapis;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.event.eventsmanagement.externalapis.apiservice.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service(value = "WeatherAPI")
public final class WeatherAPI {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeatherAPI.class);
	
	private final ApiService apiService;
	
	public WeatherAPI(ApiService service) {
		this.apiService = service;
	}
	
	public final String fetchWeather(String cityName, LocalDate date) { 
		final String apiString = apiService.getWeatherApiKey();
		final String weatherAPI = "https://gg-backend-assignment.azurewebsites.net/api/Weather"
				+ "?"
				+ "code="
				+ apiString
				+ "&"
				+ "city="
				+ cityName
				+ "&"
				+ "date="
				+ date.toString();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			final String weather = restTemplate.getForObject(weatherAPI, String.class);
			LOGGER.info("Weather API Response :- {}", weather);
			JsonNode jsonNode = mapper.readTree(weather);
			return jsonNode.get("weather").asText();
		} catch (RestClientException restClientException) {
			LOGGER.error("Weather Information Not Available right Now. "
					+ "Try Again Later.", restClientException.getMessage(), restClientException);
			return "Weather Information Not Available right Now. "
					+ "Try Again Later.";
		}
		catch (Exception exe) {
			LOGGER.error("Weather Information Not Available.", exe.getMessage(), exe);
			return "Weather Information Not Available.";
		}
	}
}