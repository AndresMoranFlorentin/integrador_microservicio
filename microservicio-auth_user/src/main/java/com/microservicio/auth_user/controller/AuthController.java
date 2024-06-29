package com.microservicio.auth_user.controller;

import com.microservicio.auth_user.dto.TokenDto;
import com.microservicio.auth_user.dto.UserCreateDto;
import com.microservicio.auth_user.dto.UserLoginDto;
import com.microservicio.auth_user.exception.CustomBadCredentialsException;
import com.microservicio.auth_user.exception.UnauthorizedTokenException;
import com.microservicio.auth_user.security.JwtTokenManager;
import com.microservicio.auth_user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class AuthController implements IAuthController{
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager jwtTokenManager;
	
	private AuthService authService;
	
	public AuthController(AuthenticationManager authenticationManager, AuthService authService, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.authService = authService;
		this.jwtTokenManager = jwtTokenManager;
	}
	
	@Override
	public ResponseEntity<TokenDto> login(UserLoginDto user) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			
		} catch (DisabledException e) {
			throw new DisabledException("User with username: " + user.getUsername() + " is disabled");
			
		} catch (BadCredentialsException e) {
			LOG.info("password incorrect");
			throw new CustomBadCredentialsException("Bad credentials", "User or password incorrect.");
		}
		
		final UserDetails userDetails = authService.loadUserByUsername(user.getUsername());
		
		final String jwtToken = jwtTokenManager.generateJwtToken(userDetails);
		
		return ResponseEntity.ok(TokenDto.builder().token(jwtToken).build());
	}

	@Override
	public ResponseEntity<HttpStatus> register(@Valid UserCreateDto user) {
		LOG.info("Register user: {}", user.getUsername());
		
		this.authService.register(user);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<TokenDto> validate(String token){
		
		String username = this.jwtTokenManager.getUsernameFromToken(token);
		UserDetails userDetails = this.authService.loadUserByUsername(username);
		
		if(this.jwtTokenManager.validateJwtToken(token, userDetails)) {
			return ResponseEntity.ok(TokenDto.builder().token(token).build());
		} else {
			throw new UnauthorizedTokenException("Unauthorized token", "Token provided is not authorized or is expired.");
		}
	}
}
