package com.event.eventsmanagement.service.eventfileservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.eventsmanagement.applicationutils.CSVHelper;
import com.event.eventsmanagement.entity.Events;
import com.event.eventsmanagement.eventsrepository.EventsRepository;

@Service(value = "EventsFileService")
public final class EventsFileService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventsFileService.class);
	
	private final EventsRepository eventsRepository;
	
	public EventsFileService(EventsRepository repository) {
		this.eventsRepository = repository;
	}
	
	public final void save(MultipartFile file) throws IOException, RuntimeException {
		try {
			List<Events> events = CSVHelper.csvToEventsParser(file.getInputStream());
			logger.info("Inserting all the Data from .csv File to the Database. IMPORT Operation Success.");
			eventsRepository.saveAll(events);
		} catch (IOException ioException) {
			logger.error("Failed to Persist/Insert the .csv File Data into Database.", ioException.getMessage(), ioException);
			throw new IOException("Failed to Persist/Insert the .csv File Data into Database." + ioException.getMessage());
		}
	}

	public final ByteArrayInputStream load() throws IOException, RuntimeException {
		try {
			List<Events> events = eventsRepository.findAll();
			ByteArrayInputStream stream = CSVHelper.eventsToCsv(events);
			logger.info("All the Data from Database writing to .csv File for EXPORT Operation.");
			return stream;
		} catch (IOException ioException) {
			logger.error("Failed to Export the Data from Database to .csv File.", ioException.getMessage(), ioException);
			throw new IOException("Failed to Export the Data from Database to .csv File." + ioException.getMessage());
		}
	}
}