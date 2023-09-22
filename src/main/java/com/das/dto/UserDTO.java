package com.das.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	Integer id;
	
	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Name must be alphabets")
	String name;
	
	@NotBlank( message = "Enter e-mail address")
    @Email(message = "Please enter a valid e-mail address")
	String email;
	
	@NotBlank(message = "Enter Password")
    @Size(min = 8, max = 15)
	String password;
	
	@Min(value = 18)
    @Max(value = 99)
	Integer age;
	
	@NotBlank(message = "Enter Gender" )
	String gender;
}
