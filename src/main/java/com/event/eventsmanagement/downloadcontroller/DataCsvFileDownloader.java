package com.event.eventsmanagement.downloadcontroller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.eventsmanagement.applicationutils.AppUtils;
import com.event.eventsmanagement.service.eventfileservice.EventFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/export"})
@Tag(name = "CSV Downloader", 
description = "API for Exporting Data from Database to .csv file")
public class DataCsvFileDownloader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataCsvFileDownloader.class);
	
	private final EventFileService eventFileService;
	
	public DataCsvFileDownloader(EventFileService eventFileService) {
		this.eventFileService = eventFileService;
	}
	
	@Operation(summary = "Export Data", description = "API for Exporting Data from Database to .csv file",
			tags = {"CSV Downloader"})
	@ApiResponse(description = "Database Data Exported",
	content = {@Content(mediaType = "text/csv")})
	@GetMapping(value = {"/csv"}, headers = {"export-csv-api-version=1"})
	public ResponseEntity<?> downloadFile() throws IOException,
	Exception {
		LOGGER.info("Initiating file download: Preparing to download CSV file containing Event data.");
		InputStreamResource file = new InputStreamResource(eventFileService.load());
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, 
		        		"attachment; filename=" + AppUtils.filename)
		        .contentType(MediaType.parseMediaType("text/csv"))
		        .body(file);
	}
}