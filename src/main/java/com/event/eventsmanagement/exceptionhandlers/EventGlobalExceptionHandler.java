package com.event.eventsmanagement.exceptionhandlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.event.eventsmanagement.eventcontroller.EventController;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice(basePackageClasses = EventController.class,
basePackages = "com.event.eventsmanagement.eventcontroller")
public final class EventGlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public final ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException,
			HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult()
		.getAllErrors()
		.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		errors.put("Requested URL", request.getRequestURI().toString());
		errors.put("Requested URL", request.getRequestURL().toString());
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public final ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException messageNotReadableException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Malformed JSON Request. Check the JSON Request Object Body.");
		errorDetails.put("errorDetails", messageNotReadableException.getLocalizedMessage());
		errorDetails.put("Requested URL",request.getRequestURI().toString());
		errorDetails.put("Requested URL", request.getRequestURL().toString());
		return new
				ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public final ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException argumentTypeMismatchException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Arguments / Params Type Mismatch.");
        errorDetails.put("errorDetails", argumentTypeMismatchException.getLocalizedMessage());
        errorDetails.put("Requested URL", request.getRequestURI().toString());
        errorDetails.put("Requested URL", request.getRequestURL().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MissingServletRequestParameterException.class})
	public final ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException requestParameterException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Check! Required Parameters are missing.");
        errorDetails.put("errorDetails", requestParameterException.getLocalizedMessage());
        errorDetails.put("Requested URL", request.getRequestURI().toString());
        errorDetails.put("Requested URL", request.getRequestURL().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public final ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException,
			HttpServletRequest request) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("message", "Internal Server Error. Check the Request Arguments / Params.");
		errorDetails.put("errorDetails", illegalArgumentException.getLocalizedMessage());
		errorDetails.put("Requested URL", request.getRequestURI().toString());
		errorDetails.put("Requested URL", request.getRequestURL().toString());
		return new
				ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
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