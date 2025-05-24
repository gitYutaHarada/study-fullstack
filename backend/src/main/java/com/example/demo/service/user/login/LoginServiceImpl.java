package com.example.demo.service.user.login;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public boolean isLogin(String loginId, String password) {
		System.out.println(loginId + password);
		return true;
	}

}
