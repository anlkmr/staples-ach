package com.emagia.ach.controller;

import com.emagia.ach.scheduler.ScheduledTasks;
import com.emagia.ach.service.Achfileservice;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ach")
@Api("ach")
@RestController
public class ACHFileController {

	@Autowired
	private Achfileservice achfileservice;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/ctx")
	public String getAchDoc(){
		return achfileservice.createOSStringAchCTXDoc();
		//return scheduledTasks.getCronValue();
	}



}