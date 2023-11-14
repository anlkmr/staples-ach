package com.emagia.ach.controller;

import com.emagia.ach.service.Achfileservice;
import com.emagia.ach.service.CashCompaniesService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ach")
@Api("ach")
@RestController
@Log4j2
public class ACHFileController {

	@Autowired
	private Achfileservice achfileservice;

	@Autowired
	private CashCompaniesService cashCompaniesService;

	@RequestMapping("/home")
	public String index() {
		log.info("in Home controller ");
		log.info("Entered - "+getClass());
		return "Greetings from Spring Boot ACH!";
	}

	@RequestMapping("/ctx")
	//@Transactional("exchangeOracleTransactionManager")
	public String getAchDoc(){
		log.info("Entered -: {}",getClass());
		return achfileservice.createOSStringAchCTXDoc();
		//return scheduledTasks.getCronValue();
	}


}