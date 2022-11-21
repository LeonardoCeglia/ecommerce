package it.objectmethod.tutorial.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.tutorial.ecommerce.dto.SupplierDto;
import it.objectmethod.tutorial.ecommerce.entity.Supplier;
import it.objectmethod.tutorial.ecommerce.mapper2.SupplierMapper;
import it.objectmethod.tutorial.ecommerce.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private SupplierRepository supplierRepository;

	
	public SupplierDto addNewOrUpdateSupplier(SupplierDto supplierDto) {
		Supplier supplierEnt = this.supplierMapper.toEntity(supplierDto);
		Supplier supplierSaved = this.supplierRepository.save(supplierEnt);
		SupplierDto _supplierDto = this.supplierMapper.toDto(supplierSaved);

		return _supplierDto;
	}
	
	
	public SupplierDto getSupplier(Long id) {
		Supplier supplier = this.supplierRepository.findById(id).orElse(null);
		SupplierDto supplierDto = this.supplierMapper.toDto(supplier);
		return supplierDto;
	}

}
