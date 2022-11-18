package it.objectmethod.tutorial.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank
	private String userNameOrMail;
	@NotBlank
	private String password;
	

}
