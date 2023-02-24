package com.vijay.springsecurity.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vijay.springsecurity.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails{
	
//	this second logic is not working
//	private String email;
//	private String password;
//	private List<GrantedAuthority> authorities;
//
//
//	public UserInfoUserDetails(UserInfo userInfo) {
//		super();
//		email = userInfo.getEmail();
//		password = userInfo.getPassword();
//		authorities = Arrays.stream(userInfo.getRoles().split(","))
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
//	}
	
	private UserInfo userInfo;

	public UserInfoUserDetails(UserInfo userInfo) {
		super();
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfo.getRoles());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
