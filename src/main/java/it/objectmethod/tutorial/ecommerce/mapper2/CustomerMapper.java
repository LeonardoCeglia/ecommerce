
package it.objectmethod.tutorial.ecommerce.mapper2;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.tutorial.ecommerce.dto.CustomerDto;
import it.objectmethod.tutorial.ecommerce.entity.Customer;

@Mapper(uses = CartMapper.class)//uses equivale al autowired perche ci dice che dobbimao utilizzare un mapper dentro un altro mapper
public interface CustomerMapper {

	@Mapping(target = "cart", source = "cartProducts")
	@Mapping(target = "token", ignore = true)
	// target fa riffeimento alla property che sts nel dto il source
	// nel entity perche il metodo è toDto
	//per ogni property che non che nn è mechata con tra entity e dto va creata un nuova annotation
	//anche per il token , ed nel caso come questo la property token esiste solo nel dto usiamo target e ignore true!!!
	public CustomerDto toDto(Customer customer);
	public CustomerDto toDto(Customer customer,String token);

	@Mapping(target = "cartProducts", source = "cart")
	// il target fa rifferimento alla property che sta nell' entity
	// è il source per il dto perche fa rifferimento all metodo
	// toEntity
	//per ogni property che non che nn è mechata con tra entity e dto va creata un nuova annotation
	public Customer toEntity(CustomerDto custDto);

	public List<CustomerDto> toDto(List<Customer> entities);

	public List<Customer> toEntity(List<CustomerDto> dtos);
}
