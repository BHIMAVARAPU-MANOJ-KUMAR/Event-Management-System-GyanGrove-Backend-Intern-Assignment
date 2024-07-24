package com.event.eventsmanagement.applicationutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component(value = "AppUtils")
/**
 * Utility classes generally contain static methods or static variables &
 * do not need Spring’s dependency injection or lifecycle management.
 * 
 * Using @Component on a utility class is unnecessary 
 * because it does not need to be instantiated or managed by Spring.
 */
public final class AppUtils {
	public static String TYPE = "text/csv";
	public static String[] HEADERs = { 
			"event_name", 
			"city_name", 
			"date", 
			"time", 
			"latitude", 
			"longitude"
			};
	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.
			ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.
    		ofPattern("H:mm:ss");
    public static String timestamp = LocalDateTime.now().
			format(DateTimeFormatter.ofPattern("d MMM uuuu 'T' HH:mm:ss.SSS"));
    public static String filename = "data_export_" + timestamp + ".csv";
}