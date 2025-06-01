package com.example.demo.util.set;

import com.example.demo.dto.SignupDto;
import com.example.demo.entity.UserInformationEntity;

public class SetUserInformation {

	public UserInformationEntity setUserInformationFromSignup(SignupDto signupDto) {
		UserInformationEntity userInformationEntity = new UserInformationEntity();
		userInformationEntity.setUserName(signupDto.getUserName());
		userInformationEntity.setPassword(signupDto.getPassword());
		return userInformationEntity;
	}
}
