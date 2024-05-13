package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.ProductOptionCreateDto;
import com.vn.mobilecity.domain.dto.request.ProductOptionUpdateDto;
import com.vn.mobilecity.domain.dto.response.ProductOptionDto;
import com.vn.mobilecity.domain.entity.ProductOption;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductOptionMapper {
    @Mappings({
            @Mapping(target = "image", ignore = true),
    })
    ProductOption mapProductOptionCreateDtoToProductOption(ProductOptionCreateDto productOptionCreateDto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "productName", source = "product.name"),
    })
    ProductOptionDto mapProductOptionToProductOptionDto(ProductOption productOption);

    List<ProductOptionDto> mapProductOptionsToProductOptionDtos(List<ProductOption> productOptions);

    @Mappings({
            @Mapping(target = "image", ignore = true),
    })
    void update(@MappingTarget ProductOption productOption, ProductOptionUpdateDto productOptionUpdateDto);
}
