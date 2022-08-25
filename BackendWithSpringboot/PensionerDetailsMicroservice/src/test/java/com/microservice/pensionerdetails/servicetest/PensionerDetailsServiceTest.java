package com.microservice.pensionerdetails.servicetest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.microservice.pensionerdetails.entity.BankDetails;
import com.microservice.pensionerdetails.entity.PensionerDetail;
import com.microservice.pensionerdetails.exception.ResourceNotFoundException;
import com.microservice.pensionerdetails.repository.PensionDetailsRepository;
import com.microservice.pensionerdetails.service.PensionDetailsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=PensionDetailsServiceImpl.class)
@AutoConfigureMockMvc
public class PensionerDetailsServiceTest {
	
	@MockBean
	private PensionDetailsRepository pensionRepository;
	
	@Autowired
	private PensionDetailsServiceImpl pensionservice;

	@Before
	public void setUp() {
	}

	@Test
	@DisplayName("Display Pension Details by Aadhaar Number")
	public void getPensionerDetailByAadhaarNumberTest_WithValidData() throws Exception {
		final Long aadhaarNumber = 123456789013L;
		BankDetails b1 = new BankDetails("RTT bank", "12345679", "Public");
		PensionerDetail p1 = new PensionerDetail(aadhaarNumber,"William", "25-02-1956", "DAJPC4150P", 30000L, 12000L, "Family", b1);
		when(pensionRepository.findByAadhaarNumber(aadhaarNumber)).thenReturn(p1);
		assertEquals(pensionservice.getdetailsByAadhaarNumber(aadhaarNumber),p1);
	}
	
	
	@After
	public void tearDown() {
	}


	
	


}
