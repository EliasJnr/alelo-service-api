package com.eliasjr.aleloserviceapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.eliasjr.aleloserviceapi.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSavedto {

	@NotBlank(message = "Name required")
	private String name;

	@Email(message = "Invalid email address")
	private String email;

	@Size(min = 7, max = 99, message = "Password must br between 7 and 99")
	private String password;

	public User transformToUser() {
		return new User(null, this.name, this.email, this.password);

	}
}
