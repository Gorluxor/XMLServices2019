package com.megatravel.security;

import java.util.ArrayList;
import java.util.List;


import com.megatravel.models.admin.User;
import com.megatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary // To make a autowire here instead of the automatic in memory one
@Service
public class ImplementedUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	//Load the user from database and automatically activate said user
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true,
				true, grantedAuthorities);
	}

}
