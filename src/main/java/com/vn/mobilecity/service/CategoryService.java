package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.CategoryRequestDto;
import com.vn.mobilecity.domain.dto.response.CategoryDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getById(Integer id);

    List<CategoryDto> getAll();

    CategoryDto create(CategoryRequestDto createDto);

    CategoryDto update(Integer id, CategoryRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);
}
