package com.microservice.authorization.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.microservice.authorization.controller.AuthorizationController;
import com.microservice.authorization.entity.UserData;
import com.microservice.authorization.exception.ResourceNotFound;
import com.microservice.authorization.repository.AuthorizationRepository;
import com.microservice.authorization.service.AuthorizationService;

@SpringBootTest(classes = AuthorizationService.class)
public class AuthorizationServiceTest {
	@Mock
	AuthorizationRepository authorizationRepository;

	@InjectMocks
	private AuthorizationService authservice;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		authservice = new AuthorizationService(authorizationRepository);
	}

	UserData getUser() {
		UserData testuser = new UserData();
		testuser.setUserName("admin");
		testuser.setPassword("admin");
		return testuser;
	}

	@Test
	public void loadUserByUserNameShouldUserNameTest() {
		UserData testuser = getUser();
		String username = testuser.getUserName();
		when(authorizationRepository.findByUserName(username)).thenReturn(testuser);
		UserDetails user = authservice.loadUserByUsername(testuser.getUserName());
		assertEquals(testuser.getUserName(), user.getUsername());
	}

	@Test
	public void LoadByNonExistingUsernameTest() {
		when(authorizationRepository.findByUserName("wronguser")).thenReturn(null);
		assertThatExceptionOfType(ResourceNotFound.class).isThrownBy(() -> {
			authservice.loadUserByUsername("wronguser");
		});
	}

}
