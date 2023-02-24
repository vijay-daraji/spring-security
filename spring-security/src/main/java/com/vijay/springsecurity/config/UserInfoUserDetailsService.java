package com.vijay.springsecurity.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vijay.springsecurity.entity.UserInfo;
import com.vijay.springsecurity.repository.UserInfoRepository;

//create for user authentication from db
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfoOptional = userInfoRepository.findByEmail(username);
		if(userInfoOptional==null) {
			throw new RuntimeException("Could not found user !!");
		}
		UserInfo userInfo = userInfoOptional.get();
		
		//convert userInfo to UserDetails
		UserInfoUserDetails userInfoUserDetails = new UserInfoUserDetails(userInfo);
		return userInfoUserDetails;
	}

}
