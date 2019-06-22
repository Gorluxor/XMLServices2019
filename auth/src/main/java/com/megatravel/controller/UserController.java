package com.megatravel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.megatravel.dtos.admin.LoginDTO;
import com.megatravel.dtos.admin.RegistrationDTO;
import com.megatravel.dtos.admin.UserDTO;
import com.megatravel.models.admin.Role;
import com.megatravel.models.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.megatravel.service.RoleService;
import com.megatravel.service.UserService;




@SuppressWarnings("Duplicates")
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(Pageable page, HttpServletRequest request) {
		
		System.out.println(request.getHeader("Authorization"));
		request.getHeader("Authorization");
		
		
		List<UserDTO> found = userService.findAll(page);
		HttpHeaders headers = new HttpHeaders();
		long usersTotal= found.size();
		headers.add("X-Total-Count", String.valueOf(usersTotal));
		
		return new ResponseEntity<>(found, headers, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Void> changeRoleUser(@PathVariable Long userId, @PathVariable Long roleId) {

		userService.changeRoleUser(userId, roleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(new UserDTO(userService.findByEmail(email)), HttpStatus.OK);
	}
	



	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		
		User user = userService.findByEmail(loginDTO.getEmail());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			String jwt = userService.signin(loginDTO.getEmail(), loginDTO.getPassword());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResponseEntity<Void> signup(@RequestBody RegistrationDTO registrationDTO) {

		if (!registrationDTO.getRepeatPassword().equals(registrationDTO.getPassword())) {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}

		User tmp = userService.findByEmail(registrationDTO.getEmail());
		if(tmp != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getName());
		user.setLastName(registrationDTO.getLastName());
		Role role = roleService.findByRoleName("Role_User");

		user.setRole(role);
		userService.signup(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
}
