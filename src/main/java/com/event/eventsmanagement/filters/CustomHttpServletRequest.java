package com.event.eventsmanagement.filters;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequest extends HttpServletRequestWrapper {
	
	private final Map<String, String> customHeaders;
	
	public CustomHttpServletRequest(HttpServletRequest request, String headerName, String headerValue) {
        super(request);
        this.customHeaders = new HashMap<>();
        this.customHeaders.put(headerName, headerValue);
    }
	
	@Override
    public String getHeader(String name) {
        String headerValue = customHeaders.get(name);
        if (headerValue != null) {
            return headerValue;
        }
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }
	
	@Override
    public Enumeration<String> getHeaderNames() {
        HttpServletRequest request = (HttpServletRequest) getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> combinedHeaders = new HashMap<>(customHeaders);
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            combinedHeaders.putIfAbsent(headerName, request.getHeader(headerName));
        }
        return Collections.enumeration(combinedHeaders.keySet());
    }
}