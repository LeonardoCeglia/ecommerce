package it.objectmethod.tutorial.ecommerce.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.objectmethod.tutorial.ecommerce.dto.CartDto;
import it.objectmethod.tutorial.ecommerce.dto.CustomerDto;
import it.objectmethod.tutorial.ecommerce.dto.ProductDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;
import it.objectmethod.tutorial.ecommerce.entity.Product;
import it.objectmethod.tutorial.ecommerce.mapper2.ProductMapper;
import it.objectmethod.tutorial.ecommerce.repository.CartRepository;
import it.objectmethod.tutorial.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CartService cartService;

	@Autowired
	private CustomerService custService;

	@Autowired
	private CartRepository cartRepo;
// qui otteniamo il prodotto selezionato 
	public ProductDto getProduct(Long id) {
		Optional<Product> p = productRepository.findById(id);
		return productMapper.toDto(p.get());
	}
// qui ci ritorna ala list adi proddotti al interno del al db 
	public List<ProductDto> getAllFreeProducts() {
		
		
			
			List<Product> p = this.productRepository.findByCart_IdCartIsNull();
			return this.productMapper.toDto(p);
		
	}
	
	
// qui aggiungiamo un nuovo  nella tabella prodotti !!!!
	public ProductDto addNewProduct(ProductDto pro) {
		Product ent = this.productMapper.toEntity(pro);
		Product newEnt = this.productRepository.save(ent);
		ProductDto proDto = this.productMapper.toDto(newEnt);
		return proDto;
		// nel service si lavora con i dto, dato che per il metodo save bisogna
		// utilizzare un entity
		// abbiamo passato entity nel save perche restituisce una eniity poi abbiamo
		// mappato essa nel toDto che sta nel mapper.
	}
	
	
// qui aggiungiamo il prodotto selezionato nel carello del customer di riferimento !!!
	public void addNewProductInTheCart(Long idCustomer, Long idProduct) {

		ProductDto proDto = getProduct(idProduct);
		CustomerDto custDto = this.custService.getCustomer(idCustomer);
		Cart _cart = cartRepo.findByCust_IdAndPagatoIsNull(idCustomer);

		// puoi solo acquistare sono se il se nell record del prodotto nella colonna di
		// disponibilita e ancora null!!!
		if (proDto.getCartId()== null) {

			if (_cart != null) {
				proDto.setCartId(_cart.getIdCart());
		
			} else {
				CartDto newCart = this.cartService.addNewCart(custDto.getId());
				proDto.setCartId(newCart.getIdCart());

			}
			this.updateProduct(proDto);
		}
	}
// qui modifichiamo un prodotto!!!
	public ProductDto updateProduct(ProductDto pro) {
		Product ent = this.productMapper.toEntity(pro);
		return this.productMapper.toDto(this.productRepository.save(ent));
	}

	public void deleteProduct(Long id) {

		this.productRepository.deleteById(id);
	}

	public void removeProductFromCart(Long id) {
		ProductDto proDto = this.getProduct(id);
		proDto.setCartId(null);
		this.updateProduct(proDto);
	}

}
