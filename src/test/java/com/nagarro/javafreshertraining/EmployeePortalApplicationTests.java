package com.nagarro.javafreshertraining;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.Assert;

import com.nagarro.javafreshertraining.controller.HelloController;

@SpringBootTest
class EmployeePortalApplicationTests {
	
	@Autowired
	HelloController hello;
	
	@Test
	@DirtiesContext
	void contextLoads() {
		Assert.notNull(hello,"Object Not Null");
	}
	
	/*
	 * 
	@Test
	void testControllerBeanMethod() {
		Assert.notNull(hello.getClass("TEST_NAME",Mockito.mock(Model.class)),"Object Not Null");
	}
	
	*/

}
