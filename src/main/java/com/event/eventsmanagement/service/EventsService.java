package com.event.eventsmanagement.service;

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

import com.event.eventsmanagement.dtos.EventsDTO;
import com.event.eventsmanagement.entity.Events;
import com.event.eventsmanagement.eventsresponse.CustomEventsResponseExternalAPI;
import com.event.eventsmanagement.eventsresponse.CustomResponseWrapper;
import com.event.eventsmanagement.externalapis.DistanceCalculationAPI;
import com.event.eventsmanagement.externalapis.WeatherAPI;
import com.event.eventsmanagement.repository.EventsRepository;

@Service(value = "EventsService")
public final class EventsService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventsService.class); 

	private final EventsRepository eventsRepository;

	public EventsService(EventsRepository repository) {
		this.eventsRepository = repository;
	}

	public final Events saveEvent(Events events) {
		logger.info("Created a New Resource in the Database & INSERT Operation Success.");
		return eventsRepository.save(events);
	}

	public final CustomResponseWrapper findEvents(double userLatitude, double userLongitude, LocalDate date, int page,
			int size) {
		Page<Events> eventsPage = eventsRepository.findByEventsWithinDateRange(date, date.plusDays(14),
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
		List<CustomEventsResponseExternalAPI> externalAPIs = eventsPage.getContent().stream().map(event -> {
			EventsDTO eventsDTO = new EventsDTO();
			eventsDTO.setEvent_name(event.getEventName());
			eventsDTO.setCity_name(event.getCityName());
			eventsDTO.setDate(event.getDate());

			CompletableFuture<String> weatherFuture = CompletableFuture
					.supplyAsync(() -> WeatherAPI.fetchWeather(event.getCityName(), event.getDate()));
			CompletableFuture<Double> distanceCalFuture = CompletableFuture.supplyAsync(() -> DistanceCalculationAPI
					.calculateDistance(userLatitude, userLongitude, event.getLatitude(), event.getLongitude()));
			CompletableFuture.allOf(weatherFuture, distanceCalFuture).join();
			return new CustomEventsResponseExternalAPI(eventsDTO, weatherFuture.join(), distanceCalFuture.join());
		}).collect(Collectors.toList());
		logger.info("Returning Available Events between Dates. Success.");
		return new CustomResponseWrapper(externalAPIs, page, size, eventsPage.getTotalElements(),
				eventsPage.getTotalPages());
	}
}