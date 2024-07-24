package com.event.eventsmanagement.datauploaddownloadcontroller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.event.eventsmanagement.csvhelper.CSVHelper;
import com.event.eventsmanagement.service.EventsFileService;

@CrossOrigin(value = {"http://localhost:8090"})
@RestController
@RequestMapping(value = {"/api/v1/import"})
public class DataCsvFileUploader {
	
	private final EventsFileService eventsFileService;
	
	public DataCsvFileUploader(EventsFileService eventsFileService) {
		this.eventsFileService = eventsFileService;
	}
	
	@PostMapping(value = {"/csv"})
	public ResponseEntity<String> uploadCsvFile(@RequestParam("file") 
	MultipartFile file) 
			throws IOException {
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