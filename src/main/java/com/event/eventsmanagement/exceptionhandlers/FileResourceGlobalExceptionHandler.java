package com.event.eventsmanagement.exceptionhandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.event.eventsmanagement.uploadcontroller.DataCsvFileUploader;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackageClasses = DataCsvFileUploader.class,
basePackages = "com.event.eventsmanagement.uploadcontroller")
public final class FileResourceGlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MissingServletRequestPartException.class})
	public final ResponseEntity<Map<String, String>> handleMissingServletRequestPartException(MissingServletRequestPartException partException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Required part 'file' is not present. Check the mutlipart form-data body.");
		errorDetails.put("errorDetails", partException.getLocalizedMessage());
		errorDetails.put("Requested URL",request.getRequestURI().toString());
		errorDetails.put("Requested URL", request.getRequestURL().toString());
		return new
				ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
	public final ResponseEntity<Map<String, String>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException mediaTypeNotSupportedException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Content-Type 'null' is not supported.");
		errorDetails.put("errorDetails", mediaTypeNotSupportedException.getLocalizedMessage());
		errorDetails.put("Requested URL",request.getRequestURI().toString());
		errorDetails.put("Requested URL", request.getRequestURL().toString());
		return new
				ResponseEntity<>(errorDetails,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {NoResourceFoundException.class})
    public final ResponseEntity<Map<String, String>> handleNoResourceFoundException(
            NoResourceFoundException noResourceFoundException, HttpServletRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", "The requested resource could not be found on the server. Please check the resource URL and try again.");
        errorDetails.put("errorDetails", noResourceFoundException.getLocalizedMessage());
        errorDetails.put("Requested URL", request.getRequestURI().toString());
        errorDetails.put("Requested URL", request.getRequestURL().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {Exception.class})
	public final ResponseEntity<Map<String, String>> handleAllExceptions(Exception exception,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", "Internal Server Error.");
        errorDetails.put("errorDetails", exception.getLocalizedMessage());
        errorDetails.put("Requested URL", request.getRequestURI().toString());
        errorDetails.put("Requested URL", request.getRequestURL().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}