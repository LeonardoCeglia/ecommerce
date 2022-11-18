package it.objectmethod.tutorial.ecommerce.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CartDto {
	private Long idCart;
	private Double price;
	private String pagato;
	private LocalDate data_acquisto;
	private LocalDate data_pagamento;
	private Long idCustomer;
	List<ProductDto> cartProducts = new ArrayList<>();
}
