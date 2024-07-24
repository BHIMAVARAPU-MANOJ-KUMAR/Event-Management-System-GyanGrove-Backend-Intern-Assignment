package com.event.eventsmanagement.datauploaddownloadcontroller;

import java.io.IOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.eventsmanagement.applicationutils.AppUtils;
import com.event.eventsmanagement.service.EventsFileService;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"api/v1/export"})
public class DataCsvFileDownloader {
	
	private final EventsFileService eventsFileService;
	
	public DataCsvFileDownloader(EventsFileService eventsFileService) {
		this.eventsFileService = eventsFileService;
	}
	
	@GetMapping(value = {"/csv"})
	public ResponseEntity<Resource> downloadFile() throws IOException {
		InputStreamResource file = new InputStreamResource(eventsFileService.load());
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, 
		        		"attachment; filename=" + AppUtils.filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
//		HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//      headers.setContentDispositionFormData("attachment", fileName);
//      headers.setContentLength(contents.length);
//      return new ResponseEntity<>(contents, headers, HttpStatus.OK);
	}
}