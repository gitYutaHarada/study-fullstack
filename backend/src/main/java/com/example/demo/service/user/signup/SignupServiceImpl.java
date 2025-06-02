package com.example.demo.service.user.signup;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignupDto;
import com.example.demo.entity.UserInformationEntity;
import com.example.demo.repository.UserInformationRepository;
import com.example.demo.util.set.SetUserInformation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService{
	
	private final UserInformationRepository repository;	
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean resistUserInformation(SignupDto signupDto) {
		//元々userNameがあるかチェック。存在したらfalse返す
		var existingUserName = repository.findById(signupDto.getUserName());
		if(existingUserName.isPresent()) {
			return false;
		}
		
		//EntityにDtoをセット
		SetUserInformation setUserInformation = new SetUserInformation();
		UserInformationEntity userInformationEntity = setUserInformation.setUserInformationFromSignup(signupDto);
		
		//パスワードをハッシュ化してEntityにセット
		userInformationEntity.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		
		//Entityをデータベースにinsert
		UserInformationEntity saved = repository.save(userInformationEntity);
		
		if(saved == null) return false;
		
		return true;
	
	}

	
}
