package it.objectmethod.tutorial.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.tutorial.ecommerce.dto.CustomerDto;
import it.objectmethod.tutorial.ecommerce.dto.LoginDto;
import it.objectmethod.tutorial.ecommerce.entity.Customer;
import it.objectmethod.tutorial.ecommerce.mapper2.CustomerMapper;
import it.objectmethod.tutorial.ecommerce.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custRep;
	@Autowired
	private CustomerMapper custMapp;
	@Autowired
	private JWTService jwtService;

	public CustomerDto getCustomer(Long id) {
	
		Customer c = custRep.findById(id).orElse(null);
//		Customer c1 = cu
//		if(c)
		
		return null;
	};

	public CustomerDto UpdateOrAddNewCustomer(CustomerDto custDto) {
		Customer customer = this.custMapp.toEntity(custDto);
		Customer newCust = this.custRep.save(customer);
		CustomerDto dto = this.custMapp.toDto(newCust);
		return dto;
	}

	public CustomerDto login(LoginDto loginDto) {

		String emailOrUserName = loginDto.getUserNameOrMail();
		String password = loginDto.getPassword();

		if (this.custRep.findByEmailAndPassword(emailOrUserName, password) == null) {
			return null;
		}
		Customer cust = this.custRep.findByEmailAndPassword(emailOrUserName, password);
		String token = this.jwtService.createJWTToken(cust);
		CustomerDto custDto = 	this.custMapp.toDto(cust, token); 
		// TODO finish creating customerdto

		return custDto;

	}


}
