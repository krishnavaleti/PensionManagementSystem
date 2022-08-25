package com.microservice.pensionerdetails.controllertest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.microservice.pensionerdetails.entity.BankDetails;
import com.microservice.pensionerdetails.entity.PensionerDetail;
import com.microservice.pensionerdetails.service.PensionDetailsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PensionerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PensionDetailsServiceImpl pensiondetailsservice;
	
	
	@Test
	@DisplayName("Find Aadhaar Number by Invalid Token")
	public void findByAadhaarNumberTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc4OTM3MCwiaWF0IjoxNjYwNzUzMzcwfQ.RXoDFQdtkyRqIMjA2ybB5u4McqUrUgODitbdutY0phk";
		BankDetails b1 = new BankDetails("RTT bank", "12345679", "Public");
		PensionerDetail p1 = new PensionerDetail(123456789013L,"William", "25-02-1956", "DAJPC4150P", 30000L, 12000L, "Family", b1);
			
		when(pensiondetailsservice.getdetailsByAadhaarNumber(123456789013L)).thenReturn(p1);	
		try {
			mockMvc.perform(get("http://localhost:8081/pensionerDetail/{aadhaarNumber}",123456789013L)
					.header("Authorization", "Bearer " + token)
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Invalid token"));
		}
	}

}
