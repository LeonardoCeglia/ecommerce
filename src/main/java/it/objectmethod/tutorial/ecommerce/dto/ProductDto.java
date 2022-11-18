package it.objectmethod.tutorial.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {

	private Long idProduct;
	@NotBlank
	private String name;
	@NotNull
	private Double price;
	private Long cartId;

}
