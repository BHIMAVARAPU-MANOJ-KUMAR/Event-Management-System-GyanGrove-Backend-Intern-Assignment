package com.event.eventsmanagement.datauploaddownloadcontroller;

import java.io.IOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.eventsmanagement.applicationutils.AppUtils;
import com.event.eventsmanagement.service.eventfileservice.EventsFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/export"})
@Tag(name = "CSV Downloader", 
description = "API for Exporting Data from Database to .csv file format")
public class DataCsvFileDownloader {
	
	private final EventsFileService eventsFileService;
	
	public DataCsvFileDownloader(EventsFileService eventsFileService) {
		this.eventsFileService = eventsFileService;
	}
	
	@Operation(summary = "Export Data", description = "API for Exporting Data from Database to .csv file",
			tags = {"CSV Downloader"})
	@ApiResponse(description = "Database Data Exported",
	content = {@Content(mediaType = "text/csv")})
	@GetMapping(value = {"/csv"}, headers = {"export-csv-api-version=1"})
	public ResponseEntity<?> downloadFile() throws IOException,
	Exception {
		InputStreamResource file = new InputStreamResource(eventsFileService.load());
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, 
		        		"attachment; filename=" + AppUtils.filename)
		        .contentType(MediaType.parseMediaType("text/csv"))
		        .body(file);
	}
}