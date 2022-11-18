package it.objectmethod.tutorial.ecommerce.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String type;
	private String token;
	private List<CartDto> cart;
}
