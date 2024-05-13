package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getById(Integer id);

    List<ProductDto> getAll(Integer categoryId);

    List<ProductDto> search(String key);

    ProductDto create(ProductRequestDto productRequestDto);

    ProductDto updateById(Integer id, ProductRequestDto productRequestDto);

    CommonResponseDto deleteById(Integer id);
}
