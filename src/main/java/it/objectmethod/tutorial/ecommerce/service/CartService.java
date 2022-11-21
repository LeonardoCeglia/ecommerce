package it.objectmethod.tutorial.ecommerce.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.objectmethod.tutorial.ecommerce.dto.CartDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;
import it.objectmethod.tutorial.ecommerce.entity.Product;
import it.objectmethod.tutorial.ecommerce.mapper2.CartMapper;
import it.objectmethod.tutorial.ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private CartRepository cartRepo;

	public CartDto getCart(Long id) {

		Cart p = cartRepo.findById(id).orElse(null);
		return this.cartMapper.toDto(p);
	}

	public CartDto addNewCart(long idCustomers) {
		CartDto cartDto = new CartDto();
		cartDto.setIdCustomer(idCustomers);
		Cart cart = this.cartMapper.toEntity(cartDto);
		Cart saved = cartRepo.save(cart);
		return this.cartMapper.toDto(saved);
	}

// aggiorno ol mio callo 
	public CartDto updateCart(CartDto cart) {

		Cart cartEnt = this.cartMapper.toEntity(cart);
		Cart savedCart = this.cartRepo.save(cartEnt);
		return this.cartMapper.toDto(savedCart);

	}

//qusto metodo mi ritorna il callo nn ancora chiuso 
	public CartDto findCartNotPaied(Long id) {
		return cartMapper.toDto(cartRepo.findByCust_IdAndPagatoIsNull(id));
	}

	// questo metodo imeplmenta l avoce pagato nel carello!!!!s
	public void paidCart(Long id) {

		Cart cartToPay = this.cartRepo.findByCust_IdAndPagatoIsNull(id);
		cartToPay.setPagato("paied");
		cartToPay.setPrice(this.getTotalAmount(cartToPay));
		LocalDate lt = LocalDate.now();
		cartToPay.setData_acquisto(lt);
		cartToPay.setData_pagamento(lt);
		this.cartRepo.save(cartToPay);
	}

	// method that return total amount!!!!!!

	public double getTotalAmount(Cart cart) {

		Double totalAmount = 0.0;

		for (Product cartObj : cart.getProd()) {
			if (cartObj.getPrice() != null) {
				totalAmount += cartObj.getPrice();
			}
		}
		return totalAmount;

	}

	// qui ottngo la conologia di mie acquisti !!!!!!!!
	public List<CartDto> purchaseHistory(Long id) {

		List<Cart> myPurchaseHistory = this.cartRepo.findByCust_IdAndPagatoIsNotNull(id);
		List<CartDto> myPurchaseHistoryDto = this.cartMapper.toDto(myPurchaseHistory);
		return myPurchaseHistoryDto;

	}
}
