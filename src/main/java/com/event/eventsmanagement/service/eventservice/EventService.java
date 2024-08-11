package com.event.eventsmanagement.service.eventservice;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.event.eventsmanagement.dto.EventFinderResponse;
import com.event.eventsmanagement.dto.EventRequest;
import com.event.eventsmanagement.dto.EventResponse;
import com.event.eventsmanagement.dto.EventResponseWithExternalAPI;
import com.event.eventsmanagement.entity.Event;
import com.event.eventsmanagement.eventrepository.EventRepository;
import com.event.eventsmanagement.externalapis.DistanceCalculationAPI;
import com.event.eventsmanagement.externalapis.WeatherAPI;

@Service(value = "EventsService")
public final class EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventService.class); 

	private final EventRepository eventsRepository;
	
	private final WeatherAPI weatherAPI;
	
	private final DistanceCalculationAPI calculationAPI;
	
	public EventService(EventRepository repository, WeatherAPI weatherAPI,
			DistanceCalculationAPI calculationAPI) {
		this.eventsRepository = repository;
		this.weatherAPI=weatherAPI;
		this.calculationAPI=calculationAPI;
	}

	public final Event saveEvent(Event events) {
		Event savedEvents = eventsRepository.save(events);
		logger.info("Successfully inserted a new event into the database. "
				+ "Event ID: {}, Event Name: {}, City: {}, Date: {}, Time: {}, Latitude: {}, Longitude: {}",
				savedEvents.getId(), savedEvents.getEventName(), savedEvents.getCityName(),
				savedEvents.getTime(), savedEvents.getDate(),
				savedEvents.getLatitude(), savedEvents.getLongitude());
		return savedEvents;
	}
	
	public final EventResponse saveEventAndGetResponse(EventRequest eventsRequest) {
		Event events2 = new Event(
				eventsRequest.getEventName(),
				eventsRequest.getCityName(),
				eventsRequest.getDate(),
				eventsRequest.getTime(),
				eventsRequest.getLatitude(),
				eventsRequest.getLongitude());
		Event savedEvents = eventsRepository.save(events2);
		logger.info("Successfully inserted a new event into the database. "
				+ "Event Name: {}, City: {}, Date: {}, Time: {}, Latitude: {}, Longitude: {}",
				savedEvents.getEventName(), savedEvents.getCityName(),
				savedEvents.getTime(), savedEvents.getDate(),
				savedEvents.getLatitude(), savedEvents.getLongitude());
		return new EventResponse(
				savedEvents.getEventName(),
				savedEvents.getCityName(),
				savedEvents.getDate(),
				savedEvents.getTime(),
				savedEvents.getLatitude(),
				savedEvents.getLongitude());
	}

	public final EventFinderResponse findEvents(double userLatitude, double userLongitude, LocalDate date, int page,
			int size) {
		logger.info("Fetching events within date range from {} to {} with page number {} and size {} ", date, date.plusDays(14), page, size);
		Page<Event> eventsPage = eventsRepository.findByEventsWithinDateRange(date, date.plusDays(14),
				PageRequest.of(page - 1, size, Sort.by("date").ascending()));
		/**
		 * Can use parallerStream() also -> but because of the smaller dataset here we
		 * are not using. For a larger dataset, for complex computation one can go for
		 * parallerStream().
		 * 
		 * The stream() and parallelStream() methods in Java's Stream
		 * API are used to create streams for processing sequences of elements. 
		 * They differ mainly in how they process the elements:
		 * 
		 * stream() 
		 * 
		 * Sequential Processing: 
		 * 
		 * By default, stream() processes elements in a
		 * sequential manner, meaning one after another. 
		 * 
		 * Single Thread: 
		 * 
		 * It uses a single thread to process the elements. 
		 * 
		 * Order: 
		 * 
		 * It preserves the order of the elements as they are processed. 
		 * 
		 * Performance: Suitable for small datasets or when operations are not computationally intensive. 
		 * 
		 * parallelStream() 
		 * 
		 * Parallel Processing: 
		 * 
		 * parallelStream() processes elements in parallel, leveraging multiple threads. 
		 * 
		 * Multiple Threads: 
		 * It divides the work among multiple threads, utilizing the available CPU cores for concurrent processing. 
		 * 
		 * Order:
		 * While it can maintain order with certain operations, the inherent parallel
		 * nature means order is not always preserved. 
		 * 
		 * Performance:
		 * 
		 * Can significantly improve performance for large datasets or computationally intensive
		 * operations due to concurrent execution. However, it also introduces overhead
		 * due to thread management and potential contention. 
		 * 
		 * When to Use Each 
		 * 
		 * Use stream() when:
		 * 
		 * You have a small dataset. Operations are simple and not computationally
		 * intensive. Order of processing is important. 
		 * 
		 * Use parallelStream() when:
		 * 
		 * You have a large dataset. Operations are computationally intensive and can
		 * benefit from parallel execution. Order is not a primary concern, or you can
		 * use operations that can handle unordered data. 
		 * 
		 * Considerations 
		 * 
		 * Thread Overhead: 
		 * 
		 * Parallel streams involve thread management overhead. For smaller
		 * datasets, the overhead might outweigh the benefits of parallel execution.
		 * 
		 * 
		 * Thread Safety: Ensure that the operations performed on the stream are
		 * thread-safe. Avoid shared mutable state unless properly synchronized.
		 * 
		 * 
		 * ForkJoinPool: Parallel streams use the ForkJoinPool for parallel execution.
		 * You can customize the pool size using ForkJoinPool.commonPool(), but be
		 * mindful of the global impact on your application.
		 * 
		 * Conclusion 
		 * 
		 * Sequential Streams (stream()) are simple and sufficient for most
		 * everyday tasks. 
		 * 
		 * Parallel Streams (parallelStream()) can offer performance
		 * benefits for large datasets and computationally intensive tasks but come with
		 * additional complexity and considerations for thread safety and overhead.
		 * Choose the one that best fits your specific use case and performance
		 * requirements.
		 */
		List<EventResponseWithExternalAPI> externalAPIs = eventsPage.getContent().stream().map(event -> {
			CompletableFuture<String> weatherFuture = CompletableFuture
					.supplyAsync(() -> weatherAPI.fetchWeather(event.getCityName(), event.getDate()));
			CompletableFuture<Double> distanceCalFuture = CompletableFuture.supplyAsync(() -> calculationAPI
					.calculateDistance(userLatitude, userLongitude, event.getLatitude(), event.getLongitude()));
			CompletableFuture.allOf(weatherFuture, distanceCalFuture).join();
			
			return new EventResponseWithExternalAPI(event.getEventName(),
					event.getCityName(),
					event.getDate(),
					weatherFuture.join(), 
					distanceCalFuture.join());
		}).collect(Collectors.toList());
		logger.info("Returned available events from {} to {} ", date, date.plusDays(14));
		return new EventFinderResponse(externalAPIs, page, size, eventsPage.getTotalElements(),
				eventsPage.getTotalPages());
	}
}