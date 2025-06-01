package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SignupDto;
import com.example.demo.service.user.signup.SignupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignupController {
	
	private final SignupService service;

	@PostMapping("/api/signup")
	@ResponseBody
	public boolean signup(@RequestBody SignupDto signupDto) {
		return service.resistUserInformation(signupDto);
	}
}
