package com.megatravel.dto;

import com.megatravel.model.Role;
import com.megatravel.model.User;

public class UserDTO {
	private Long id;
	private String name;
	private String lastName;
	private String  email;
	private Role role;

	public UserDTO() { }
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.setName(user.getName());
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.setRole(user.getRole());
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
