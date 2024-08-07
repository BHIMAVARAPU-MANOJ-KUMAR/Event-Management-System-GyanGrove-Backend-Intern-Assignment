package com.event.eventsmanagement.externalapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.event.eventsmanagement.externalapis.apiservice.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service(value = "DistanceCalculationAPI")
public final class DistanceCalculationAPI {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistanceCalculationAPI.class);
	
	private final ApiService service;
	
	public DistanceCalculationAPI(ApiService apiService) {
		this.service=apiService;
	}
	
	public final Double calculateDistance(Double latitude1,
			Double longitude1,
			Double latitude2,
			Double longitude2) {
		final String apiString = service.getDistanceApiKey();
		final String distanceCalculationAPI = "https://gg-backend-assignment.azurewebsites.net/api/Distance"
				+ "?"
				+ "code="
				+ apiString
				+ "&"
				+ "latitude1="
				+ latitude1
				+ "&"
				+ "longitude1="
				+ longitude1
				+ "&"
				+ "latitude2="
				+ latitude2
				+ "&"
				+ "longitude2="
				+ longitude2;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			final String distanceCalculation = restTemplate.getForObject(distanceCalculationAPI, 
					String.class);
			JsonNode jsonNode = mapper.readTree(distanceCalculation);
			return jsonNode.get("distance").asDouble();
		} catch (Exception e) {
			LOGGER.error("Failed to parse distance from the API.", e.getMessage(), e);
			throw new RuntimeException("Failed to parse distance from the API.", e);
		}
	}
}