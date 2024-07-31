package com.event.eventsmanagement.datauploaddownloadcontroller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.event.eventsmanagement.applicationutils.CSVHelper;
import com.event.eventsmanagement.service.eventfileservice.EventsFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/import"})
@Tag(name = "CSV Uploader",
description = "API for Importing Data from .csv file to Database")
public class DataCsvFileUploader {
	
	private final EventsFileService eventsFileService;
	
	public DataCsvFileUploader(EventsFileService eventsFileService) {
		this.eventsFileService = eventsFileService;
	}
	
	@Operation(summary = "Import Data",
			tags = {"CSV Uploader"},
			description = "API for Importing Data from .csv file to Database")
	@ApiResponse(content = {@Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)},
	description = "File Data Imported")
	@PostMapping(value = {"/csv"}, headers = {"import-csv-api-version=1"},
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadCsvFile(@RequestParam("file") 
	MultipartFile file) 
			throws IOException, Exception {
		String message = "";
		if (CSVHelper.hasCSVFormat(file)) {
		try {
			eventsFileService.save(file);
			message = "File Uploaded Successfully. " + file.getOriginalFilename();
			ResponseEntity<String> responseEntity = new ResponseEntity<>(
					message, HttpStatus.OK);
			return responseEntity;
		} catch (IOException ioExe) {
			ioExe.getCause();
			ioExe.getMessage();
		}
	}
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
				"Please Upload a CSV File.", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
}