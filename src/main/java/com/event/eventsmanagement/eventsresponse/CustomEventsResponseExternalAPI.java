package com.event.eventsmanagement.eventsresponse;

import com.event.eventsmanagement.dtos.EventsDTO;

public record CustomEventsResponseExternalAPI(EventsDTO events,
		String weather,
		Double distance_km) {}