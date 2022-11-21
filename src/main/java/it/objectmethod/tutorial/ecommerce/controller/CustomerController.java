package it.objectmethod.tutorial.ecommerce.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.tutorial.ecommerce.dto.CustomerDto;
import it.objectmethod.tutorial.ecommerce.dto.LoginDto;
import it.objectmethod.tutorial.ecommerce.service.CartService;
import it.objectmethod.tutorial.ecommerce.service.CustomerService;
import it.objectmethod.tutorial.ecommerce.service.JWTService;

@RestController

public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartService cartService;
	@Autowired
	private JWTService jwtService;

	@GetMapping("api/cust/get-customer")
	// qui metto il request header
	public CustomerDto getCust(@RequestHeader("auth-token") String token) {
		Long id = this.jwtService.getUserIdByToken(token);
		CustomerDto cust1 = customerService.getCustomer(id);
		cust1.setToken(token);
		return cust1;
	}

	@PostMapping("api/add-customer")
	public CustomerDto addCustomer(@RequestBody CustomerDto custDto) {

		return this.customerService.UpdateOrAddNewCustomer(custDto);
	}

	@PutMapping("api/update-customer")
	public CustomerDto updateCustomer(@RequestBody CustomerDto custDto) {	      

		return this.customerService.UpdateOrAddNewCustomer(custDto);

	}

	@PostMapping("pay")
	public void payTheCart(@RequestHeader("auth-token") String token) {
		Long id = this.jwtService.getUserIdByToken(token);
		this.cartService.paidCart(id);
	}

	@GetMapping("api/login")
	public ResponseEntity<CustomerDto> login(@Valid   @RequestBody LoginDto loginDto) {
		CustomerDto dto = this.customerService.login(loginDto);
		if (dto == null) {
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
		}

	}

}
