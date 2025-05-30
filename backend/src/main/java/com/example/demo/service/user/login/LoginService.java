package com.example.demo.service.user.login;

import java.util.List;

import com.example.demo.entity.UserInformationEntity;

public interface LoginService {

	boolean isLogin(String loginId, String password);
	
	List<UserInformationEntity> searchUserById(String userName);
}
