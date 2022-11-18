package it.objectmethod.tutorial.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.tutorial.ecommerce.dto.CartDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;
import it.objectmethod.tutorial.ecommerce.entity.Customer;

@Component
public class CartMapper {
	@Autowired
	public ProductMapper proMapper;

	public CartDto toDto(Cart cart) {

		CartDto cartDto = new CartDto();

		cartDto.setIdCart(cart.getIdCart());
		cartDto.setPrice(0.00);
		cartDto.setIdCustomer(cart.getCust() == null ? null : cart.getCust().getId());
		cartDto.setPagato(cart.getPagato());
		cartDto.setData_acquisto(cart.getData_acquisto());
		cartDto.setData_pagamento(cart.getData_pagamento());
		cartDto.setCartProducts(proMapper.productDtoList(cart.getProd()));
		return cartDto;
	};

	public List<CartDto> listCartDto(List<Cart> cart) {

		List<CartDto> listDto = new ArrayList<CartDto>();
		if (cart != null) {
			for (Integer i = 0; i < cart.size(); i++) {
				listDto.add(toDto(cart.get(i)));
			}
		}
		return listDto;

	}

// to Entity converts dto an entity value
	public Cart toEntity(CartDto dto) {

		Cart cartEnt = new Cart();
		cartEnt.setIdCart(dto.getIdCart());
		cartEnt.setPrice(dto.getPrice());
		cartEnt.setPagato(dto.getPagato());
		cartEnt.setData_acquisto(dto.getData_acquisto());
		cartEnt.setData_pagamento(dto.getData_pagamento());
		if (dto.getIdCustomer() != null) {
			Customer c = new Customer();
			c.setId(dto.getIdCustomer());
			cartEnt.setCust(c);
			cartEnt.setProd(this.proMapper.productList(dto.getCartProducts()));
		}
		return cartEnt;

	}

}
