package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.service.user.login.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;
	
	
	@PostMapping("/api/login")
	@ResponseBody
	public boolean login(@RequestBody LoginRequestDto loginRequestDto) {
		if(service.isLogin(loginRequestDto.getUserId(), loginRequestDto.getPassword())) {
			System.out.println("true");
			return true;
		} else {
			System.out.println("false");
			return false;
		}
	}
}
