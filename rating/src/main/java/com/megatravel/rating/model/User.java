package com.megatravel.rating.model;


import com.megatravel.rating.validation.StaticData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
    @Size(min= StaticData.minLength, max=StaticData.lengthValue)
	private String name;
	@NotNull
    @Size(min=StaticData.minLength, max=StaticData.lengthValue)
	private String lastName;
	@NotNull
	private String password;
	@NotNull
	private String salt;

	@NotNull
    @Size(min=StaticData.minLengthEmail, max=StaticData.maxLengthEmail)
	private String email;
	@OneToOne
	private Role role;
		
	public User() {
		
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
