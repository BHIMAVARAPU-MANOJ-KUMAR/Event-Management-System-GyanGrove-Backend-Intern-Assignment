package com.event.eventsmanagement.dtos;

import java.util.List;

public record EventsFinderResponse(List<EventsResponseWithExternalAPIs> 
events,
		Integer page,
		Integer pageSize,
		Long totalEvents,
		Integer totalPages) {}