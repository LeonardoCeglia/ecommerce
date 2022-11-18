package it.objectmethod.tutorial.ecommerce.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cart")
	private Long idCart;
	@Column(name = "price")
	private Double price;
	@Column(name = "pagato")
	private String pagato;
	@Column(name = "data_acquisto")
	private LocalDate data_acquisto;
	@Column(name = "data_pagamento")
	private LocalDate data_pagamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_customer")
	private Customer cust; // id customer dato che usaimo la relazione many to one con la foreignkey l id ce lo va a ceracre dentro il customer che instanziamo dentro il cart 
	                      //e tramite le annotation sa che quella properti si trova dentro il customer!!!! 
	@OneToMany(mappedBy = "cart")
	List<Product> prod;
}
