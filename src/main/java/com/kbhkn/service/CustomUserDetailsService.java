package com.kbhkn.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kbhkn.repository.UserRepository;

/**
 * Created by kbhkn on 20.09.2016 16:41.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	public UserRepository userService;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.kbhkn.domain.User user = userService.findByUsername(username);
		return buildUserForAuthentication(user, buildUserAuthority(user));
	}

	private User buildUserForAuthentication(com.kbhkn.domain.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(com.kbhkn.domain.User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		if (user.getRole() != null)
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}
}
