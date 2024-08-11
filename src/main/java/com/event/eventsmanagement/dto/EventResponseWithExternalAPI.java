package com.event.eventsmanagement.dto;

import java.time.LocalDate;

public record EventResponseWithExternalAPI(String event_name,
		String city_name,
		LocalDate date,
		String weather,
		Double distance_km) {}