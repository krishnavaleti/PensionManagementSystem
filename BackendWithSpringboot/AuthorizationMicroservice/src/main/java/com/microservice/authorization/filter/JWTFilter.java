package com.microservice.authorization.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservice.authorization.service.AuthorizationService;
import com.microservice.authorization.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;


@Component
public class JWTFilter extends OncePerRequestFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	public JWTFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}



	//filtering jwt token from authorization header starting with string bearer 
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException,ExpiredJwtException {
		String authrizationHeader=request.getHeader("Authorization");
		
		String token=null;
		String userName=null;
		
		
		if(authrizationHeader !=null && authrizationHeader.startsWith("Bearer"))
		{
			token=authrizationHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			
			if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
		}
		
		filterChain.doFilter(request, response);
	}

}
