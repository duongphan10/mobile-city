package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductDto;
import com.vn.mobilecity.domain.dto.response.ProductSimpleDto;

public interface ProductService {
    ProductDto getById(Integer id);

    PaginationResponseDto<ProductSimpleDto> getAll(Integer categoryId, PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<ProductSimpleDto> search(PaginationFullRequestDto paginationFullRequestDto);

    ProductDto create(ProductRequestDto productRequestDto);

    ProductDto updateById(Integer id, ProductRequestDto productRequestDto);

    CommonResponseDto deleteById(Integer id);
}
