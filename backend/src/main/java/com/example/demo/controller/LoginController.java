package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.ErrorMessageConst;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.service.user.login.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;
	
	private final MessageSource messageSource;
	
	
	@PostMapping("/api/login")
	@ResponseBody
	public String login(@RequestBody LoginRequestDto loginRequestDto) {
		
		if(service.isLogin(loginRequestDto.getUserId(), loginRequestDto.getPassword())) {
			System.out.println("true");
			return "true";
		} else {
			System.out.println("false");
			String errMsg = AppUtil.getMessage(messageSource, ErrorMessageConst.LOGIN_WRONG_INPUT);
			return errMsg;
		}
	}
}
