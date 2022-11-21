package it.objectmethod.tutorial.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.tutorial.ecommerce.dto.ProductDto;
import it.objectmethod.tutorial.ecommerce.service.JWTService;
import it.objectmethod.tutorial.ecommerce.service.ProductService;

@RestController

public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private JWTService jwtService;

	@GetMapping("api/{id}")
	public ProductDto getProduct(@PathVariable Long id) {
		return this.productService.getProduct(id);
	}

	@GetMapping("")
	public List<ProductDto> getAllProduct() {

		return this.productService.getAllFreeProducts();
	}

	@PostMapping("/addToCart")
	public void addToCart(@RequestHeader("auth-token") String token, @RequestParam Long idProduct) {
		Long id = this.jwtService.getUserIdByToken(token);
		this.productService.addNewProductInTheCart(id, idProduct);
	}
	

	@PostMapping("api/addPro")
	public ProductDto addNewProduct(@Valid @RequestBody ProductDto pro) {
		ProductDto pro1 = productService.addNewProduct(pro);
		return pro1;
	}

	@PutMapping("api/updatePro")
	public ProductDto updateProduct(@Valid @RequestBody ProductDto pro) {
		ProductDto pro1 = productService.addNewProduct(pro);
		return pro1;
	}

	@DeleteMapping("api/{id}")
	void deleteProduct(@PathVariable Long id) {

		this.productService.deleteProduct(id);
	}

	@PutMapping("api/remove-product/{id}")
	public void updateProduct(@PathVariable Long id) {
		this.productService.removeProductFromCart(id);
	}
}
