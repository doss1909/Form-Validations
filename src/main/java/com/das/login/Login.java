package com.das.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Login {
	
	@NotBlank( message = "Enter e-mail address")
    @Email(message = "Please enter a valid e-mail address")
	String email;
	
	@NotBlank(message = "Enter Password")
    @Size(min = 8, max = 15)
	String password;
}
