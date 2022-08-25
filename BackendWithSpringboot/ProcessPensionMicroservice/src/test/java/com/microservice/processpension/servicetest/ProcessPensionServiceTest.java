package com.microservice.processpension.servicetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.microservice.processpension.client.AuthClient;
import com.microservice.processpension.client.PensionDetailClient;
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
public class ProcessPensionServiceTest {

	@Autowired
	private ProcessPensionService processpensionservice;

	@MockBean
	private ProcessPensionRepository processpensionrepository;
	
	@MockBean
	private PensionTransactionDetailsRepository transactionrepository;
	
	@MockBean
	private PensionDetailClient client;

	@MockBean
	private AuthClient authclient;

	
	@Test
	@DisplayName("Valid Pension Details for Public and Self Type")
	public void calculatePensionAmountTest_Public_Self() {
		PensionDetails p1 = new PensionDetails(1,"123456789021","12345687",41000.0,500);
		assertNotNull(processpensionservice.calculatePensionAmount("Public", 70000.0, 2000, "Self","12345687","123456789021"));

	}
	
	@Test
	@DisplayName("Valid Pension Detail for Private and Family Type")
	public void calculatePensionAmountTest_Private_Family() {
		PensionDetails p2 = new PensionDetails(1,"123456789019","12345685",49550.0,550);
		assertNotNull(processpensionservice.calculatePensionAmount("Private", 98000.0, 5500, "Family","12345685","123456789019"));
	}
	
	@Test
	@DisplayName("Valid Pension Detail for Private and Self Type")
	public void calculatePensionAmountTest_Private_Self() {
		PensionDetails p3 = new PensionDetails(1,"123456789020","12345686",41000.0,550);
		assertNotNull(processpensionservice.calculatePensionAmount("Private", 50000.0, 1000, "Self","12345686","123456789020"));
	}
	
	@Test
	@DisplayName("Valid Pension Detail for Public and Family Type")
	public void calculatePensionAmountTest_Public_Family() {
		PensionDetails p4 = new PensionDetails(1,"123456789018","12345684",59000.0,500);
		assertNotNull(processpensionservice.calculatePensionAmount("Public", 100000.0, 9000, "Family","12345684","123456789018"));
	}
	
	@Test
	@DisplayName("getPensionerDetailTest for Invalid Token")
	public void getPensionerDetailTest(){
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc4OTM3MCwiaWF0IjoxNjYwNzUzMzcwfQ.RXoDFQdtkyRqIMjA2ybB5u4McqUrUgODitbdutY0phk";
		PensionDetails p4 = new PensionDetails(1,"123456789025","12345691",46000.0,500);
		ProcessPensionInput ppi = new ProcessPensionInput("123456789025");
		try {
			when(processpensionservice.getPensionerDetail(token, ppi)).thenReturn(p4);	
			when(processpensionservice.calculatePensionAmount("Public", 55000.0, 2000, "Self","12345691","123456789025"));
		} catch (ResourceNotFoundException e) {
			assertTrue(e.getMessage().contains("Invalid Pensioner Input"));
		}
		
	}
	@Test
	@DisplayName("getTransaction Details for Invalid Token")
	public void getTransactionDetails() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc4OTM3MCwiaWF0IjoxNjYwNzUzMzcwfQ.RXoDFQdtkyRqIMjA2ybB5u4McqUrUgODitbdutY0phk";
		PensionDetails p4 = new PensionDetails(1,"123456789025","12345691",46000.0,500);
		PensionTransactionDetail pt=new PensionTransactionDetail(1,"123456789025",41000.0,"12345691");
		try {
			when(processpensionservice.savePensionTransactionDetail(token, p4)).thenReturn(pt);	
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Invalid Token"));
		}
		
	}
	

	@After
	public void tearDown() {
	}
}
