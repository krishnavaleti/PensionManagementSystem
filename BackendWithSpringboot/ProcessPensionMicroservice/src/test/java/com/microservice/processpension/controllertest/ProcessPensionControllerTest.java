package com.microservice.processpension.controllertest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.microservice.processpension.client.PensionDetailClient;
import com.microservice.processpension.controller.ProcessPensionController;
import com.microservice.processpension.entity.PensionDetails;
import com.microservice.processpension.entity.PensionTransactionDetail;
import com.microservice.processpension.entity.ProcessPensionInput;
import com.microservice.processpension.exception.ResourceNotFoundException;
import com.microservice.processpension.repository.PensionTransactionDetailsRepository;
import com.microservice.processpension.repository.ProcessPensionRepository;
import com.microservice.processpension.service.ProcessPensionService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProcessPensionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProcessPensionService pensionservice;
	
	
	@Test
	@DisplayName("findByAadhaarNumberTest by Invalid Token")
	public void findByAadhaarNumberTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc4OTM3MCwiaWF0IjoxNjYwNzUzMzcwfQ.RXoDFQdtkyRqIMjA2ybB5u4McqUrUgODitbdutY0phk";
		String body = "{\"aadhaarNumber\":\"" + "123456789025" + "\"}";
		ProcessPensionInput processPensionInput = new ProcessPensionInput("123456789012");
		PensionDetails p1 = new PensionDetails(1,"123456789025","12345691",46000.0,500);
		when(pensionservice.getPensionerDetail(token, processPensionInput)).thenReturn(p1);	
		try {
			mockMvc.perform(post("/processPension")
					.header("Authorization", "Bearer " + token)
					.content(body)
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Invalid Pensioner Input"));
		}
	}
	
	@Test
	@DisplayName("Save Transaction Details by Invalid Token")
	public void saveTransactionDetail() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc4OTM3MCwiaWF0IjoxNjYwNzUzMzcwfQ.RXoDFQdtkyRqIMjA2ybB5u4McqUrUgODitbdutY0phk";
		String body = "{\"aadhaarNumber\":\"" + "123456789025" + "\", \"accountNumber\":\"" + "12345691" + "\", \"pensionAmount\":\"" + 46000.0 + "\", \"bankServiceCharge\":\"" + 500 + "\"}";
		
		PensionDetails p1 = new PensionDetails(1,"123456789025","12345691",46000.0,500);
		PensionTransactionDetail ptd=new PensionTransactionDetail(1,"123456789025",45500.0,"12345691");
		when(pensionservice.savePensionTransactionDetail(token, p1)).thenReturn(ptd);	
		try {
			mockMvc.perform(post("/ProcessPensionTransaction")
					.header("Authorization", "Bearer " + token)
					.content(body)
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Pension Detail not coreect"));
		}
	}
	
	@AfterEach
	public void tearDown() {
	}

	
	

}
