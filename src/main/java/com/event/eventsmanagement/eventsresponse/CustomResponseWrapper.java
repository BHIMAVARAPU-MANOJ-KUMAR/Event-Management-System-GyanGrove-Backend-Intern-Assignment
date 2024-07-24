package com.event.eventsmanagement.eventsresponse;

import java.util.List;

public record CustomResponseWrapper(List<CustomEventsResponseExternalAPI> 
events,
		Integer page,
		Integer pageSize,
		Long totalEvents,
		Integer totalPages) {}