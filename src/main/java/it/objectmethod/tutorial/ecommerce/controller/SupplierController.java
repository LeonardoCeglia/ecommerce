package it.objectmethod.tutorial.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.tutorial.ecommerce.dto.SupplierDto;
import it.objectmethod.tutorial.ecommerce.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("api/add-new-supplier")
	public SupplierDto addSupplier(@RequestBody SupplierDto supplierDto) {

		SupplierDto suppDto = this.supplierService.addNewOrUpdateSupplier(supplierDto);

		return suppDto;

	}
	@GetMapping("api/get-supplier/{id}")
	public SupplierDto getSupplier(@PathVariable Long id) {
		
		SupplierDto suppDto = this.supplierService.getSupplier(id);
		return suppDto;
	}

}
