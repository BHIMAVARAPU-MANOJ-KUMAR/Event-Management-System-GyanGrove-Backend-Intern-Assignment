package com.event.eventsmanagement.service.eventfileservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.eventsmanagement.applicationutils.CSVHelper;
import com.event.eventsmanagement.entity.Event;
import com.event.eventsmanagement.eventrepository.EventRepository;

@Service(value = "EventFileService")
public final class EventFileService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventFileService.class);
	
	private final EventRepository eventRepository;
	
	public EventFileService(EventRepository repository) {
		this.eventRepository = repository;
	}
	
	public final void save(MultipartFile file) throws IOException, RuntimeException {
		try {
			List<Event> events = CSVHelper.csvToEventsParser(file.getInputStream());
			logger.info("Successfully inserted all data from the .csv file into the database. IMPORT operation completed.");
			eventRepository.saveAll(events);
		} catch (IOException ioException) {
			logger.error("Failed to Persist/Insert the .csv File Data into Database: {} ", ioException.getMessage(), ioException);
			throw new IOException("Failed to Persist/Insert the .csv File Data into Database." + ioException.getMessage());
		}
	}

	public final ByteArrayInputStream load() throws IOException, RuntimeException {
		try {
			List<Event> events = eventRepository.findAll();
			ByteArrayInputStream stream = CSVHelper.eventsToCsv(events);
			logger.info("All the Data from Database writing to .csv File. EXPORT operation completed.");
			return stream;
		} catch (IOException ioException) {
			logger.error("Failed to Export the Data from Database to .csv File: {} ", ioException.getMessage(), ioException);
			throw new IOException("Failed to Export the Data from Database to .csv File." + ioException.getMessage());
		}
	}
}