package com.event.eventsmanagement.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public final class EventResponseDTO {
	private String eventName;
	private String cityName;
	private LocalDate date;
	private LocalTime time;
	private Double latitude;
	private Double longitude;
	
	public EventResponseDTO(String event_name,
			String city_name,
			LocalDate _date,
			LocalTime _time,
			Double _latitude,
			Double _longitude) {
		this.eventName=event_name;
		this.cityName=city_name;
		this.date=_date;
		this.time=_time;
		this.latitude=_latitude;
		this.longitude=_longitude;
	}
}