package it.objectmethod.tutorial.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.objectmethod.tutorial.ecommerce.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
