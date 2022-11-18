package it.objectmethod.tutorial.ecommerce.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import it.objectmethod.tutorial.ecommerce.dto.ProductDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;
import it.objectmethod.tutorial.ecommerce.entity.Product;

@Component
public class ProductMapper {

	public ProductDto toDto(Product pro) {
		ProductDto proDto = new ProductDto();
		proDto.setIdProduct(pro.getIdProduct());
		proDto.setName(pro.getName());
		proDto.setPrice(pro.getPrice());
		proDto.setCartId(pro.getCart()==null?null:pro.getCart().getIdCart());
		return proDto;
	}
	

	public List<ProductDto> productDtoList(List<Product> pro) {
		List<ProductDto> listPro = new ArrayList<ProductDto>();
		for (Integer i = 0; i < pro.size(); i++) {
			listPro.add(toDto(pro.get(i)));
		}

		return listPro;
	}
	
	public List<Product> productList(List<ProductDto> pro) {
		List<Product> listProEnt = new ArrayList<Product>();
		for (Integer i = 0; i < pro.size(); i++) {
			listProEnt.add(toEntity(pro.get(i)));
		}

		return listProEnt;
	}
	
	

	public Product toEntity(ProductDto proDto) {		
		Product pro = new Product();
		pro.setIdProduct(proDto.getIdProduct());
		pro.setName(proDto.getName());
		pro.setPrice(proDto.getPrice());
		if (proDto.getCartId() != null) {
			Cart cart = new Cart();
			cart.setIdCart(proDto.getCartId());
			pro.setCart(cart);
		}

		return pro;
	}

}
