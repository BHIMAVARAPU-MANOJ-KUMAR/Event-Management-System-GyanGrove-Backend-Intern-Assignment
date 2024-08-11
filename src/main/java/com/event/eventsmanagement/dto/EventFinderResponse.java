package com.event.eventsmanagement.dto;

import java.util.List;

public record EventFinderResponse(List<EventResponseWithExternalAPI> 
events,
		Integer page,
		Integer pageSize,
		Long totalEvents,
		Integer totalPages) {}