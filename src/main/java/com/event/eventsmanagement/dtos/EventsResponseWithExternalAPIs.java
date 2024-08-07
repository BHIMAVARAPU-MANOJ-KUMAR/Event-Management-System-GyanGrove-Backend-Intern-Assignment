package com.event.eventsmanagement.dtos;

import java.time.LocalDate;

public record EventsResponseWithExternalAPIs(String event_name,
		String city_name,
		LocalDate date,
		String weather,
		Double distance_km) {}