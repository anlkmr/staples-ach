package com.emagia.ach.controller;

import com.emagia.ach.scheduler.ScheduledTasks;
import com.emagia.ach.service.Achfileservice;
import com.emagia.ach.service.CashCompaniesService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/ctx")
	//@Transactional("exchangeOracleTransactionManager")
	public String getAchDoc(){
		log.info("Entered -: {}",getClass());
		return achfileservice.createOSStringAchCTXDoc();
		//return scheduledTasks.getCronValue();
	}

	@RequestMapping("/cash")
	//@Transactional("exchangeOracleTransactionManager")
	public String getCash(){
		return cashCompaniesService.getIDresult("023");
		//return scheduledTasks.getCronValue();
	}




}