package com.event.eventsmanagement.eventscontroller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event.eventsmanagement.dtos.EventResponseDTO;
import com.event.eventsmanagement.entity.Events;
import com.event.eventsmanagement.eventsresponse.CustomResponseWrapper;
import com.event.eventsmanagement.service.EventsService;

import jakarta.validation.Valid;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/events"})
@Validated
@ControllerAdvice
public class EventsController {
	
	private final EventsService eventsService;
	
	public EventsController(EventsService service) {
		this.eventsService = service;
	}
	
	@PostMapping(value = {"/event"}, headers = {"event-api-version=1"}, produces = {"application/v1+json"})
	public ResponseEntity<Events> createNewEvent(@RequestBody @Valid Events events) throws Exception {
		Events savedEvents = eventsService.saveEvent(events);
		ResponseEntity<Events> responseEntity = new ResponseEntity<>(
				savedEvents, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PostMapping(value = {"/events"}, headers = {"events-api-version=2"}, produces = {"application/v2+json"})
	public ResponseEntity<EventResponseDTO> createEvent(@RequestBody @Valid Events events) throws Exception {
		EventResponseDTO dto = eventsService.saveEventAndGetResponse(events);
		ResponseEntity<EventResponseDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping(value = {"/find"}, headers = {"events-finder-api-version=1"},produces = {"application/json"})
	public ResponseEntity<CustomResponseWrapper> findEvents(@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam int page,
			@RequestParam int size) throws Exception {
		CustomResponseWrapper events = eventsService.findEvents(latitude, longitude, date, page,
				size);
		return ResponseEntity.ok(events);
	}
}