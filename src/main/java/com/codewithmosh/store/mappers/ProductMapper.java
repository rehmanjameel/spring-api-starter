package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dto.ProductDto;
import com.codewithmosh.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto userToProductDto(Product product);

    // add the product
    Product toEntity(ProductDto productDto);

    // update the product
    @Mapping(target = "id",  ignore = true)
    void updateProduct(ProductDto productDto, @MappingTarget Product product);

}
