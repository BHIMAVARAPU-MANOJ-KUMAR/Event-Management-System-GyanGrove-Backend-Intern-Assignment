package com.event.eventsmanagement.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public final class EventsDTO {
	private String event_name;
	private String city_name;
	private LocalDate date;
}