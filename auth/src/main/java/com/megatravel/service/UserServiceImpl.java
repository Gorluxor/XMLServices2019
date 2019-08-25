package com.megatravel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.megatravel.dtos.admin.RegistrationDTO;
import com.megatravel.exceptions.CustomException;
import com.megatravel.models.admin.User;

import com.megatravel.models.types.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtos.admin.UserDTO;

import com.megatravel.jwt.JwtTokenUtils;
import com.megatravel.models.admin.Role;
import com.megatravel.password.Base64Utility;
import com.megatravel.password.HashPassword;
import com.megatravel.repository.RoleRepository;
import com.megatravel.repository.UserRepository;

@SuppressWarnings("Duplicates")
@Component
public class UserServiceImpl {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JwtTokenUtils jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	RoleServiceImpl roleService;

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
			System.out.println("email " + email);
			if(user != null) {
				System.out.println("pronadjen korisnik ");
			}
			HashPassword hashPassword = new HashPassword();
			System.out.println("kreirao hash ");
			byte[] hashed;
			System.out.println("kreirao promenljivu ");
			try {
				hashed = hashPassword.hashPassword(password, Base64Utility.decode(user.getSalt()));
				System.out.println("kreirao hash 2");
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, Base64Utility.encode(hashed)));
				System.out.println("autentifikovao se ");
			} catch (Exception e) {
				System.out.println("Nije prosla autentifikacije");
				e.printStackTrace();
			}

			System.out.println("proslo sve, kreiranje tokena");
			return jwtTokenProvider.createToken(email, user.getRole());
		} catch (AuthenticationException e) {
			System.out.println("Ne valja username i password ");
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
			user.setLastChangedDate(new Date());
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
			user.setLastChangedDate(new Date());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid role id");
		}	
	}


	public List<User> findFreeAgents(){
		return userRepository.findByRole_IdAndAccommodationIsNull(3l); // 3 == ROLE_AGENT
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

	public void registerAgent(RegistrationDTO registrationDTO){

		User tmp = userRepository.findByEmail(registrationDTO.getEmail());
		if(tmp != null) {
			return;
		}

		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getName());
		user.setLastName(registrationDTO.getLastName());
		user.setPib(registrationDTO.getPib());
		user.setLocation(registrationDTO.getLocationDTO() == null ? null : new Location(registrationDTO.getLocationDTO()));
		Role role = roleService.findByRoleName("ROLE_AGENT");
		user.setLastChangedDate(new Date());
		user.setRole(role);
		signup(user);
	}


}
