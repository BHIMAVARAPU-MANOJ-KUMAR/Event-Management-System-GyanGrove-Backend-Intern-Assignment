package com.event.eventsmanagement.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventsRequest {
	
	@NotNull(message = "Event Name must not be Null")
	@NotEmpty(message = "Event Name must not be Empty")
	@NotBlank(message = "Event Name is mandatory")
	private String eventName;
	
	@NotNull(message = "Event City Name must not be Null")
	@NotEmpty(message = "Event City Name must not be Empty")
	@NotBlank(message = "Event City is mandatory")
	private String cityName;
	
	@NotNull(message = "Event Date is mandatory")
	private LocalDate date;
	
	@NotNull(message = "Event Time is mandatory")
	private LocalTime time;
	
	@NotNull(message = "Latitude Coordinates are mandatory")
	private Double latitude;
	
	@NotNull(message = "Longitude Coordinates are mandatory")
	private Double longitude;
	
	public EventsRequest(String event_name,
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