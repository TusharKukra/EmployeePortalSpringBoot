package com.nagarro.javafreshertraining;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTests {
	
	/*
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	void testWebRequest() {
		assertThat(this.testRestTemplate.getForObject("http://localhost:"+port+"/",String.class))
		.contains("This is my First Spring Boot Application");
	}
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testWebRequest() throws Exception{
		assertThat(this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()));
	}
	
	*/
}
