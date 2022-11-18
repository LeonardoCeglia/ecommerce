package it.objectmethod.tutorial.ecommerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.tutorial.ecommerce.dto.CustomerDto;
import it.objectmethod.tutorial.ecommerce.entity.Customer;
@Component
public class CustomerMapper {
	@Autowired
	public CartMapper cartMapper;
	public CustomerDto toDto(Customer cust) {
		CustomerDto custDto = new CustomerDto();
		custDto.setId(cust.getId());
		custDto.setFirstName(cust.getFirstName());
		custDto.setLastName(cust.getLastName());
		custDto.setEmail(cust.getEmail());
		custDto.setPassword(cust.getPassword());
		custDto.setType(cust.getType());
		custDto.setCart(cartMapper.listCartDto(cust.getCartProducts())==null?null :cartMapper.listCartDto(cust.getCartProducts()));
		return custDto;	
	}
	
	public CustomerDto toDto(Customer cust, String token) {
		CustomerDto custDto = this.toDto(cust);
		custDto.setToken(token);
		return custDto;
	}
	
	public Customer toEntity(CustomerDto dto) {
		Customer cust = new Customer();
		cust.setId(dto.getId());
		cust.setFirstName(dto.getFirstName());
		cust.setLastName(dto.getLastName());
		cust.setEmail(dto.getEmail());
		cust.setPassword(dto.getPassword());
		cust.setType(dto.getType());
		return cust;
		
		
	}
	

}
