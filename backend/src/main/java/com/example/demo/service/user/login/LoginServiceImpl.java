package com.example.demo.service.user.login;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInformationEntity;
import com.example.demo.repository.UserInformationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final UserInformationRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean isLogin(String userName, String password) {
		System.out.println(passwordEncoder.encode(password));
		for(UserInformationEntity entity : searchUserById(userName)) {
			if(passwordEncoder.matches(password, entity.getPassword()) && entity.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<UserInformationEntity> searchUserById(String userName) {
		return repository.findAllByUserName(userName);
	}

}
