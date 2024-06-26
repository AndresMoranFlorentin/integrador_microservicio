package com.microservicio.auth_user.controller;

import com.microservicio.auth_user.dto.TokenDto;
import com.microservicio.auth_user.dto.UserCreateDto;
import com.microservicio.auth_user.dto.UserLoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import jakarta.validation.Valid;

@RequestMapping("/api/auth")
@Validated
public interface IAuthController {
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<TokenDto> login(@RequestBody @Valid UserLoginDto user);
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<HttpStatus> register(@RequestBody @Valid UserCreateDto user);
	
	@GetMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<TokenDto> validate(@RequestParam(name = "token") String token);
}
