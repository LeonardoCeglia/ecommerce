package it.objectmethod.tutorial.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_supplier")
	private Long idSupplier;
	@Column(name = "name")
	private String name;
	@Column(name = "p_iva")
	private String pIVA;
	@OneToMany(mappedBy ="supplier")
	List<Product> product;
	
}
