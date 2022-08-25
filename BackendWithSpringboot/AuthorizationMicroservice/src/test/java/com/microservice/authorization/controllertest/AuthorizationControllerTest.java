package com.microservice.authorization.controllertest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.authorization.controller.AuthorizationController;
import com.microservice.authorization.entity.UserData;
import com.microservice.authorization.filter.JWTFilter;
import com.microservice.authorization.service.AuthorizationService;
import com.microservice.authorization.util.JWTUtil;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthorizationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserDetails userDetails;

	@MockBean
	private AuthorizationService authService;

	@MockBean
	private JWTUtil jwtUtil;

	@Autowired
	private JWTFilter filter;

	@MockBean
	private AuthenticationManager authenticationManager;

	private UserData user;
	
	@Test
	@DisplayName("Generate token for valid user")
	public void createAuthenticationTokenTest() throws Exception {
		User user = new User("admin", "admin", new ArrayList<>());
		String body = "{\"username\":\"" + user.getUsername() + "\", \"password\":\" " + user.getPassword() + "\"}";
		String jwtToken = jwtUtil.generateToken(user.getUsername());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/authenticate").content(body)
				.header("Authorization", "Bearer " + jwtToken).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON);
		when(authService.loadUserByUsername(eq("user1"))).thenReturn(user);
		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	@DisplayName("Invalid token")
	public void authorizationTest() throws Exception {
		User user = new User("wronguser", "password", new ArrayList<>());
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MDc1NTYzMCwiaWF0IjoxNjYwNzE5NjMwfQ.BVpsF2XuUobfnxIaAn4Nqr0L457P0VPmolGmO8sySWY";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/authorize")
				.header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		when(authService.loadUserByUsername(eq("wronguser"))).thenReturn(user);
		when(jwtUtil.validateToken(token.substring(7),user)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isUnauthorized()).andReturn();
	
	}
	
	@After
	public void tearDown() {
	}
}
	


