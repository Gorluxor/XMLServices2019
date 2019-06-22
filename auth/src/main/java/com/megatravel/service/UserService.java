package com.megatravel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.megatravel.exceptions.CustomException;
import com.megatravel.models.admin.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtos.admin.UserDTO;

import com.megatravel.jwt.JwtTokenUtils;
import com.megatravel.models.admin.Role;
import com.megatravel.password.Base64Utility;
import com.megatravel.password.HashPassword;
import com.megatravel.repository.RoleRepository;
import com.megatravel.repository.UserRepository;


@Component
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JwtTokenUtils jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public List<UserDTO> findAll(Pageable page) {
		Page<User> users = userRepository.findAll(page);

		if(users.hasContent()) {
			List<UserDTO> retVal = new ArrayList<UserDTO>();
	
			for (User user : users) {
				UserDTO userDTO = new UserDTO(user);
				retVal.add(userDTO);
			}

			return retVal;
		}
		else {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No users!");
		}
	}

	public UserDTO findOne(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {

			return new UserDTO(user.get());
		}
		else {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No user with requested id");
		}
	}
	
	public User findOneUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {

			return user.get();
		}
		else {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No user with requested id");
		}
	}

	public User save(User user) {

		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.deleteById(id);

	}
	
	public String signin(String email, String password) {
		try {
			User user = userRepository.findByEmail(email);
			HashPassword hashPassword = new HashPassword();
			byte[] hashed;
			try {
				hashed = hashPassword.hashPassword(password, Base64Utility.decode(user.getSalt()));
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, Base64Utility.encode(hashed)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRole());
		} catch (AuthenticationException e) {

			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public User signup(User user) {
		HashPassword hashPassword = new HashPassword();
		if (!userRepository.existsByEmail(user.getEmail())) {
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			byte[] salt = hashPassword.generateSalt();
			byte[] password = hashPassword.hashPassword(user.getPassword(), salt);
			user.setPassword(Base64Utility.encode(password));
			user.setSalt(Base64Utility.encode(salt));
			return userRepository.save(user);
		} else {
			throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void changeRoleUser(Long userId, Long roleId) {
		User user = this.findOneUser(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(role.isPresent()) {
			user.setRole(role.get());
			userRepository.save(user);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid role id");
		}	
	}
	

}
