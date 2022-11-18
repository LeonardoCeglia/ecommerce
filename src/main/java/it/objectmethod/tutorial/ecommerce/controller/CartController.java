package it.objectmethod.tutorial.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.tutorial.ecommerce.dto.CartDto;
import it.objectmethod.tutorial.ecommerce.service.CartService;
import it.objectmethod.tutorial.ecommerce.service.JWTService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private JWTService jwtService;

	@GetMapping("/{id}")
	CartDto getCart(@PathVariable Long id) {
		CartDto cart1 = cartService.getCart(id);
		return cart1;
	}

//	@PostMapping("/addCart")
//	CartDto addNewCart(@RequestHeader("auth-token") String token) {
//		Long id = this.jwtService.getUserIdByToken(token);
//		CartDto cart1 = this.cartService.addNewCart(id);
//		
//		return cart1;
//	}
//	@PutMapping("")
//	CartDto updateCart(@RequestBody CartDto cart) {
//		CartDto cart1 = cartService.updateCart(cart);
//		return cart1;
//	}

	@GetMapping("/findNotPaiedCart")
	public CartDto findNotPaiedCart(@RequestParam Long  id) {

		return this.cartService.findCartNotPaied(id);
	}

	@GetMapping("/purchaseHistory")
	public List<CartDto> getPurchaseHistory(@RequestHeader("auth-token") String token) {
		Long id = this.jwtService.getUserIdByToken(token);
		List<CartDto> myPurchaseHistory = this.cartService.purchaseHistory(id);
		return myPurchaseHistory;
	}

}
