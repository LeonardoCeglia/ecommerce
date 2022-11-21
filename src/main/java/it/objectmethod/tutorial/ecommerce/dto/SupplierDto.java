package it.objectmethod.tutorial.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SupplierDto {
	
	private Long idSupplier;
	private String name;
	@NotNull
	private String pIVA;
	List<ProductDto> productDto= new ArrayList<>();

}
