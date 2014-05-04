package com.codeproof.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.exception.UserNotFoundException;
import com.codeproof.model.User;
import com.codeproof.spec.UserBusinessService;

@Service("userBusinessService")
public class UserBusinessServiceImpl implements UserBusinessService, UserDetailsService {

	@Autowired
	UserDataService userDataService;

	@Override
	public User find(String userId) throws UserNotFoundException {
		return userDataService.find(userId);
	}

	@Override
	public void save(User user) {
		userDataService.save(user);
	}

	@Override
	public void update(User user) {
		userDataService.update(user);
	}

	public List<GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (role.intValue() == 1) {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (role.intValue() == 2) {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authList;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDataService.loadUserByUsername(userName);
		
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),true,true,true,true,getAuthorities(Integer.valueOf(user.getUserRole().getRoleId())));
		return userDetail;
	}

}
