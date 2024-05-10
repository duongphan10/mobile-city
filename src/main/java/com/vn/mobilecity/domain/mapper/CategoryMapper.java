package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.CategoryRequestDto;
import com.vn.mobilecity.domain.dto.response.CategoryDto;
import com.vn.mobilecity.domain.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    Category mapCategoryCreateDtoToCategory(CategoryRequestDto createDto);

    CategoryDto mapCategoryToCategoryDto(Category category);

    List<CategoryDto> mapCategoryToCategoryDto(List<Category> categories);

    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    void updateCategory(@MappingTarget Category category, CategoryRequestDto updateDto);
}
