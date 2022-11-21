package it.objectmethod.tutorial.ecommerce.mapper2;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import it.objectmethod.tutorial.ecommerce.dto.ProductDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;
import it.objectmethod.tutorial.ecommerce.entity.Product;

@Mapper
public interface ProductMapper {
	// target fa riffeimento alla property che sts nel dto il source
	// nel entity perche il metodo è toDto
	@Mapping(target="supplierId",source = "supplier.idSupplier")
	@Mapping(target = "cartId", source = "cart.idCart")
	public ProductDto toDto(Product pro);
///////////////////////////////////////////////////////////////////////////////////
	// il target fa rifferimento alla property che sta nell' entity
	// è il source per il dto perche fa rifferimento all metodo
	// toEntity
	@Mapping(target="supplier.idSupplier",source="supplierId" )
	@Mapping(target = "cart", ignore = true)
	public Product toEntity(ProductDto proDto);
///////////////////////////////////////////////////////////////////////////////////

	public List<ProductDto> toDto(List<Product> product);

	public List<Product> toEntity(List<ProductDto> productDto);
	
	@AfterMapping
	default void mapCart(@MappingTarget Product product, ProductDto dto) {
		if (dto != null && dto.getCartId() != null) {
			Cart cart = new Cart();
			cart.setIdCart(dto.getCartId());
			product.setCart(cart);
		}

	}
}
