package it.objectmethod.tutorial.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.tutorial.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "select * from products where name_product= ? and available is null ", nativeQuery = true)
	public Product findProductInMyCart(String nameProduct);

	public List <Product> findByCart_IdCartIsNull();
}
