package com.megatravel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
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

import com.megatravel.service.RoleServiceImpl;
import com.megatravel.service.UserServiceImpl;




@SuppressWarnings("Duplicates")
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	RoleServiceImpl roleServiceImpl;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(Pageable page, HttpServletRequest request) {
		
		System.out.println(request.getHeader("Authorization"));
		request.getHeader("Authorization");
		
		
		List<UserDTO> found = userServiceImpl.findAll(page);
		HttpHeaders headers = new HttpHeaders();
		long usersTotal= found.size();
		headers.add("X-Total-Count", String.valueOf(usersTotal));
		
		return new ResponseEntity<>(found, headers, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Void> changeRoleUser(@PathVariable Long userId, @PathVariable Long roleId) {

		userServiceImpl.changeRoleUser(userId, roleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		return new ResponseEntity<>(userServiceImpl.findOne(id), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(new UserDTO(userServiceImpl.findByEmail(email)), HttpStatus.OK);
	}

	@RequestMapping(value = "/block/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> disableUser(@PathVariable(name = "userId") Long userId){
		userServiceImpl.blockUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/activate/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> activateUser(@PathVariable(name = "userId") Long userId) {
		userServiceImpl.activateUser(userId);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId){
		userServiceImpl.remove(userId);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@RequestMapping(value = "/agentRegister", method = RequestMethod.POST)
	public ResponseEntity<String> registerAgent(@RequestBody RegistrationDTO registrationDTO){
		userServiceImpl.registerAgent(registrationDTO);
		return new ResponseEntity<>("Added agent", HttpStatus.OK);
	}

	@RequestMapping(value = "/free", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserDTO>> getFreeAgents(){
		List<User> list = userServiceImpl.findFreeAgents();
		List<UserDTO> values = new ArrayList<>();
		for (User u : list){
			values.add(new UserDTO(u));
		}
		return new ResponseEntity<>(values, HttpStatus.OK);
	}




	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

		System.out.println("u metodi "  + loginDTO.getEmail());
		User user = userServiceImpl.findByEmail(loginDTO.getEmail());
		if(user == null) {
			System.out.println("Nema korisnika");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			System.out.println("Kreiranje jwt-a " + loginDTO.getPassword());
			String jwt = userServiceImpl.signin(loginDTO.getEmail(), loginDTO.getPassword());
			System.out.println("Kreirao jwt ");
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("Inicijalizovao maper ");
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

		User tmp = userServiceImpl.findByEmail(registrationDTO.getEmail());
		if(tmp != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getName());
		user.setLastName(registrationDTO.getLastName());
		user.setBirthday(registrationDTO.getBirthday());
		user.setPhoneNumber(registrationDTO.getPhoneNumber());
		user.setCountry(registrationDTO.getCountry());
		user.setActivatedUser(true);
		Role role = roleServiceImpl.findByRoleName("USER");

		user.setRole(role);
		userServiceImpl.signup(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
}
