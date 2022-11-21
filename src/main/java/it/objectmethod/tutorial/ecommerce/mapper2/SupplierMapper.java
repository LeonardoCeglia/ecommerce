package it.objectmethod.tutorial.ecommerce.mapper2;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.tutorial.ecommerce.dto.SupplierDto;
import it.objectmethod.tutorial.ecommerce.entity.Supplier;

@Mapper(uses = ProductMapper.class)
public interface SupplierMapper {

	@Mapping(target="productDto",source="product")
	public SupplierDto toDto(Supplier supplier);
	
	@Mapping(target="product",source="productDto")
	public Supplier toEntity(SupplierDto supplierDto);
	
	public List<SupplierDto> toDto(List<Supplier> entities);

	public List<Supplier> toEntity(List<SupplierDto> dtos);
}
