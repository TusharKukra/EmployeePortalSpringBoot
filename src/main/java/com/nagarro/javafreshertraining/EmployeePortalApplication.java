package com.nagarro.javafreshertraining;

import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.slf4j.Logger;

@SpringBootApplication
@EnableCaching
public class EmployeePortalApplication {

	public static final Logger logger = LoggerFactory.getLogger(EmployeePortalApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeePortalApplication.class, args);
		logger.debug("This is Debug Message");
		logger.info("This is Info Message");
		logger.warn("This is Warn Message");
		logger.error("This is Error Message");
		
	}

}
