package com.event.eventsmanagement.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(value = "CustomHeaderFilter")
public class CustomHeaderFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpServletRequest modifiedRequest = request;
		
		if (request.getHeader("import-csv-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequestWrapper(modifiedRequest, "import-csv-api-version", "1");
		}
		
		if (request.getHeader("export-csv-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequestWrapper(modifiedRequest, "export-csv-api-version", "1");
		}
		
		if (request.getHeaders("event-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequestWrapper(modifiedRequest, "event-api-version", "1");
		}
		
		if (request.getHeaders("events-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequestWrapper(modifiedRequest, "events-api-version", "2");
		}
		
		if (request.getHeaders("events-finder-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequestWrapper(modifiedRequest, "events-finder-api-version", "1");
		}
		
		filterChain.doFilter(modifiedRequest, response);
	}
}