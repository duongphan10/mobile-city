package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.domain.dto.response.ProductDto;
import com.vn.mobilecity.domain.dto.response.ProductSimpleDto;
import com.vn.mobilecity.domain.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true),
            @Mapping(target = "promotionValue", source = "promotionValue"),
    })
    Product mapProductRequestDtoToProduct(ProductRequestDto productRequestDto);

    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "categoryName", source = "category.name"),
            @Mapping(target = "promotionId", source = "promotion.id"),
            @Mapping(target = "promotionName", source = "promotion.name"),
    })
    ProductDto mapProductToProductDto(Product product);

    List<ProductDto> mapProductsToProductDtos(List<Product> products);

    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "categoryName", source = "category.name"),
            @Mapping(target = "productOptionDtos", source = "productOptions"),
            @Mapping(target = "promotionId", source = "promotion.id"),
            @Mapping(target = "promotionName", source = "promotion.name"),
    })
    ProductSimpleDto mapProductToProductSimpleDto(Product product);

    List<ProductSimpleDto> mapProductsToProductSimpleDtos(List<Product> products);

    @Mappings({
            @Mapping(target = "avatar", ignore = true),
    })
    void update(@MappingTarget Product product, ProductRequestDto productRequestDto);
}
