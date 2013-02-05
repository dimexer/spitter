package com.dimexer.spitter.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.service.SpitterService;

public class CustomUserDetailsService implements UserDetailsService {

	private SpitterService spitterService;

	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		System.out.println(arg0);
		Spitter user = spitterService.findSpitterByUsername(arg0);
		if (user == null){
			throw new UsernameNotFoundException(
					"No user with such username found!");
		
		}
		spitterService.updateLastLoginTime(user);
		return user;
	}

	public SpitterService getSpitterService() {
		return spitterService;
	}

	public void setSpitterService(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

}
