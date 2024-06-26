package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.ProductOptionCreateDto;
import com.vn.mobilecity.domain.dto.request.ProductOptionUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductOptionDto;

import java.util.List;

public interface ProductOptionService {
    ProductOptionDto getById(Integer id);

    List<ProductOptionDto> getAllProductOption(Integer productId);
    List<ProductOptionDto> searchProductOption(Integer productId, Integer ram, Integer rom, String color);

    ProductOptionDto create(ProductOptionCreateDto productOptionCreateDto);

    ProductOptionDto updateById(Integer id, ProductOptionUpdateDto productOptionUpdateDto);

    CommonResponseDto deleteById(Integer id);
}
