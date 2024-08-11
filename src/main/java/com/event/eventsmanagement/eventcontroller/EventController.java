package com.event.eventsmanagement.eventcontroller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.event.eventsmanagement.dto.EventFinderResponse;
import com.event.eventsmanagement.dto.EventRequest;
import com.event.eventsmanagement.dto.EventResponse;
import com.event.eventsmanagement.entity.Event;
import com.event.eventsmanagement.service.eventservice.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/events"})
@RestControllerAdvice
public class EventController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
	
	private final EventService eventsService;
	
	public EventController(EventService service) {
		this.eventsService = service;
	}
	
	@Tag(name = "Event Creation Version-1",
			description = "API for creation of New Event")
	@Operation(summary = "Event Creation v1",
	tags = {"Event Creation Version-1"},
	description = "Create New Event")
	@ApiResponse(content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Event.class))},
	description = "New Event Created Successfully")
	@PostMapping(value = {"/event"}, headers = {"event-api-version=1"}, produces = {"application/v1+json"})
	public ResponseEntity<Event> createNewEvent(@Valid @RequestBody Event events) throws Exception {
		LOGGER.info("Create New Event API (Version 1) invoked.");
		Event savedEvents = eventsService.saveEvent(events);
		LOGGER.info("Http Status: {} Http Status Code: {} Http Series: {} ", HttpStatus.CREATED.toString(), HttpStatusCode.valueOf(201).toString(), Series.SUCCESSFUL.toString());
		ResponseEntity<Event> responseEntity = new ResponseEntity<>(
				savedEvents, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@Tag(name = "Event Creation Version-2",
			description = "API for creation of New Event")
	@Operation(summary = "Event Creation v2",
	tags = {"Event Creation Version-2"},
	description = "Create New Event")
	@ApiResponse(content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventResponse.class))},
	description = "New Event Created Successfully")
	@PostMapping(value = {"/events"}, headers = {"events-api-version=2"}, produces = {"application/v2+json"})
	public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest events) throws Exception {
		LOGGER.info("Create New Event API (Version 2) invoked.");
		EventResponse dto = eventsService.saveEventAndGetResponse(events);
		LOGGER.info("Http Status: {} Http Status Code: {} Http Series: {} ", HttpStatus.CREATED.toString(), HttpStatusCode.valueOf(201).toString(), Series.SUCCESSFUL.toString());
		ResponseEntity<EventResponse> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@Tag(name = "Event Finder", 
			description = "API to Find Events based on the User Geographical Location Latitude, Longitude and Date")
	@Operation(summary = "Event Finder",
	tags = {"Event Finder"},
	description = "To Find Events based on the User specified details like Latitude, Longitude and Date")
	@ApiResponse(content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventFinderResponse.class))},
	description = "Fetched all the Available Events from given Date till 14 days")
	@GetMapping(value = {"/find"}, headers = {"events-finder-api-version=1"}, produces = {"application/json"})
	public ResponseEntity<EventFinderResponse> findEvents(@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam int page,
			@RequestParam int size) throws Exception {
		LOGGER.info("FindEvents API (Version 1) invoked. Finding events from {} to {} ", date, date.plusDays(14));
		EventFinderResponse events = eventsService.findEvents(latitude, longitude, date, page,
				size);
		LOGGER.info("Http Status: {} Http Status Code: {} Http Series: {} ", HttpStatus.OK.toString(), HttpStatusCode.valueOf(200).toString(), Series.SUCCESSFUL.toString());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(events);
	}
}