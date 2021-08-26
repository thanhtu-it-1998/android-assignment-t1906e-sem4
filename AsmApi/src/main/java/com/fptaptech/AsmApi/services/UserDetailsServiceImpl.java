package com.fptaptech.AsmApi.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fptaptech.AsmApi.models.User;
import com.fptaptech.AsmApi.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = new ArrayList<User>();
		User userObj = new User();
		this.userRepository.findAll().forEach(users::add);
		for(User user:users) {
			if(user.getUsername() == username) {
				userObj = user;
			}
		}
		
		if (userObj == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userObj.getUsername(), userObj.getPassword(),
				Collections.emptyList());
	}
}
