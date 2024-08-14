package com.event.eventsmanagement.applicationutils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.event.eventsmanagement.entity.Event;

//@Component(value = "CSVHelper")
/**
 * Utility classes generally contain static methods or static variables &
 * do not need Spring’s dependency injection or lifecycle management.
 * 
 * Using @Component on a utility class is unnecessary 
 * because it does not need to be instantiated or managed by Spring.
 */
public final class CSVHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVHelper.class);
	
	public static final boolean hasCSVFormat(MultipartFile file) {
		if (!AppUtils.TYPE.equalsIgnoreCase(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	public static final List<Event> csvToEventsParser(InputStream inputStream) throws IOException, RuntimeException {
		try (BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat
						.DEFAULT
						.builder()
						.setHeader()
						.setIgnoreHeaderCase(true)
						.setTrim(true)
						.build());
				){
			
			List<Event> events = new ArrayList<Event>();
			
			Iterable<CSVRecord> csvRecordIterable = csvParser.getRecords();
			
			for (CSVRecord record : csvRecordIterable) {
				String eventName = record.get("event_name");
				String cityName = record.get("city_name");
				String dateString = record.get("date");
				LocalDate date = LocalDate.parse(dateString, AppUtils.dateFormatter);
				String timeString = record.get("time");
				LocalTime time = LocalTime.parse(timeString, AppUtils.timeFormatter);
				String latitudeString = record.get("latitude");
				String longitudeString = record.get("longitude");
				double latitude = Double.parseDouble(latitudeString);
                double longitude = Double.parseDouble(longitudeString);
				Event events1 = new Event(eventName,
						cityName,
						date,
						time,
						latitude,
						longitude);
				
				events.add(events1);
			}
			LOGGER.info("CSV file successfully parsed into Event data.");
			return events;
		} catch (IOException ioException) {
			LOGGER.error("Failed to parse CSV file into Event data: {} ", ioException.getMessage(), ioException);
			throw new IOException("Failed to Parse CSV File into Event data." + ioException.getMessage());
		}
	}
	
	public static final ByteArrayInputStream eventsToCsv(List<Event> events) throws IOException, RuntimeException{
		final CSVFormat format = CSVFormat.DEFAULT.
				builder()
				.setQuoteMode(QuoteMode.MINIMAL)
				.setHeader(AppUtils.HEADERs)
				.setIgnoreHeaderCase(true)
				.setTrim(true)
				.build();
		
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for(Event events2 : events) {
				List<String> dataList = Arrays.asList(
						events2.getEventName(),
						events2.getCityName(),
						String.valueOf(events2.getDate().format(AppUtils.dateFormatter)),
						String.valueOf(events2.getTime().format(AppUtils.timeFormatter)),
						String.valueOf(events2.getLatitude()),
						String.valueOf(events2.getLongitude())
						);
				csvPrinter.printRecord(dataList);
			}
			csvPrinter.flush();
			LOGGER.info("Successfully exported Event data to CSV file.");
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException ioException) {
			LOGGER.error("Failed to export Event data to CSV file: {} " + ioException.getMessage(), ioException);
			throw new RuntimeException("Failed to Export data to CSV file." + ioException.getMessage());
		}
	}
}