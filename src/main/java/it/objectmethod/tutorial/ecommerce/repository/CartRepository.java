package it.objectmethod.tutorial.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.tutorial.ecommerce.entity.Cart;

@Repository

public interface CartRepository extends JpaRepository<Cart, Long> {

//	@Query(value = "SELECT * FROM carts WHERE id_customer = ? AND pagato is null", nativeQuery = true )
//	Cart findNotPaiedCart(Long num);
	Cart findByCust_IdAndPagatoIsNull(@Param("id") Long id);

//	@Query(value = "SELECT * FROM carts WHERE id_customer = ? AND pagato is not null ORDER BY data_acquisto asc ", nativeQuery = true )
//	List <Cart> historyOfCart(Long num);
	List<Cart> findByCust_IdAndPagatoIsNotNull(@Param("id") Long id);// qua abbiamo applicato il salto di entity per
																		// accedere al property id di cust che e
																		// instanziata nel cat entity ho usato l
																		// underscore piu in nome della properti che mi
																		// serve findByCust-->(_Id)<--AndPagatoIsNotNull

}
