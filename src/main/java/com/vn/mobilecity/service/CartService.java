package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.CartCreateDto;
import com.vn.mobilecity.domain.dto.request.CartUpdateDto;
import com.vn.mobilecity.domain.dto.response.CartDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;

public interface CartService {

    PaginationResponseDto<CartDto> getAll(Integer userId, PaginationFullRequestDto paginationFullRequestDto);

    int getNumberItem(Integer userId);

    CartDto create(Integer userId, CartCreateDto cartCreateDto);

    CartDto updateById(Integer userId, Integer id, CartUpdateDto cartUpdateDto);

    CommonResponseDto deleteById(Integer userId, Integer id);

}
