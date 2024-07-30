package com.event.eventsmanagement.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(value = "CustomHeaderFilter")
public class CustomHeadersFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpServletRequest modifiedRequest = request;
		
		if (request.getHeader("import-csv-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequest(modifiedRequest, "import-csv-api-version", "1");
		}
		
		if (request.getHeader("export-csv-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequest(modifiedRequest, "export-csv-api-version", "1");
		}
		
		if (request.getHeaders("event-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequest(modifiedRequest, "event-api-version", "1");
		}
		
		if (request.getHeaders("events-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequest(modifiedRequest, "events-api-version", "2");
		}
		
		if (request.getHeaders("events-finder-api-version") == null) {
			modifiedRequest = new CustomHttpServletRequest(modifiedRequest, "events-finder-api-version", "1");
		}
		
		filterChain.doFilter(modifiedRequest, response);
	}
}