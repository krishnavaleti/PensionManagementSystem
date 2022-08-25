package com.microservice.authorization.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.microservice.authorization.filter.JWTFilter;
import com.microservice.authorization.service.AuthorizationService;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthorizationService authorizationService;
	
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private JWTFilter jwtFilter;

	@Autowired
	public SecurityConfig(JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint, AuthorizationService authorizationService, JWTFilter jwtFilter) {
		this.authorizationService = authorizationService;
		this.jwtFilter = jwtFilter;
		this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authorizationService);

		super.configure(auth);
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@SuppressWarnings("deprecation")
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();

	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/authenticate").permitAll();
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/authorize").authenticated().and().exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().authorizeRequests().antMatchers(HttpMethod.OPTIONS)
        .permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.csrf().ignoringAntMatchers("/eureka/**");
        httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
}

	}

