package com.event.eventsmanagement.eventsresponse;

import java.util.List;

public record EventsFinderResponse(List<EventsResponseWithExternalAPIs> 
events,
		Integer page,
		Integer pageSize,
		Long totalEvents,
		Integer totalPages) {}