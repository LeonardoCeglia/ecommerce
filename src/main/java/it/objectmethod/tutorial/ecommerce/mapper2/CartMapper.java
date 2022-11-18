package it.objectmethod.tutorial.ecommerce.mapper2;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.tutorial.ecommerce.dto.CartDto;
import it.objectmethod.tutorial.ecommerce.entity.Cart;

@Mapper(uses = ProductMapper.class)//uses equivale al autowired perche ci dice che dobbimao utilizzare un mapper dentro un altro mapper
public interface CartMapper {
	@Mapping(target = "cartProducts", source = "prod")
	@Mapping(target = "idCustomer", source = "cust.id")
	
	// target fa riffeimento alla property che sts nel dto il source
	// nel entity perche il metodo è toDto
	//per ogni property che non che nn è mechata con tra entity e dto va creata un nuova annotation
	public CartDto toDto(Cart cart);
///////////////////////////////////////////////////////////////////////////////////
	@Mapping(target = "prod", source = "cartProducts")
	@Mapping(target = "cust.id", source = "idCustomer")
	// il target fa rifferimento alla property che sta nell' entity
	// è il source per il dto perche fa rifferimento all metodo
	// toEntity
	//per ogni property che non che nn è mechata con tra entity e dto va creata un nuova annotation
	public Cart toEntity(CartDto cartDto);
///////////////////////////////////////////////////////////////////////////////////
	public List<CartDto> toDto(List<Cart> cart);

	public List<Cart> toEntity(List<CartDto> cartDto);
}
