package com.event.eventsmanagement.externalapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DistanceCalculationAPI {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistanceCalculationAPI.class);
	
	public static final Double calculateDistance(Double latitude1,
			Double longitude1,
			Double latitude2,
			Double longitude2) {
		final String distanceCalculationAPI = "https://gg-backend-assignment.azurewebsites.net/api/Distance"
				+ "?"
				+ "code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ=="
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
			LOGGER.info("Distance API Response :- {}", distanceCalculation);
			JsonNode jsonNode = mapper.readTree(distanceCalculation);
			return jsonNode.get("distance").asDouble();
		} catch (Exception e) {
			LOGGER.error("Failed to parse distance from API response", e.getMessage(),e);
			throw new RuntimeException("Failed to parse distance from API response", e);
		}
	}
}