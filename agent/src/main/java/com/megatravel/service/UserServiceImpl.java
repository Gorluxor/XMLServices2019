package com.megatravel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import com.megatravel.models.admin.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtos.admin.UserDTO;

import com.megatravel.models.admin.Role;

import com.megatravel.repository.RoleRepository;
import com.megatravel.repository.UserRepository;

@SuppressWarnings("Duplicates")
@Component
public class UserServiceImpl {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;



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
	

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void changeRoleUser(Long userId, Long roleId) {
		User user = this.findOneUser(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(role.isPresent()) {
			user.setRole(role.get());
			userRepository.save(user);
			user.setLastChangedDate(new Date());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid role id");
		}	
	}



	public void blockUser(Long id) throws ResponseStatusException {
		User user = userRepository.getOne(id);
		user.setLastChangedDate(new Date());
		if (user.getRole().getRoleName().contains("USER")){
			user.setActivatedUser(false);
			userRepository.save(user);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not user type");
		}
	}

	public void activateUser(Long id) throws ResponseStatusException {
		User user = userRepository.getOne(id);
		user.setLastChangedDate(new Date());
		if (user.getRole().getRoleName().contains("USER")){
			user.setActivatedUser(true);
			userRepository.save(user);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not user type");
		}

	}


}
