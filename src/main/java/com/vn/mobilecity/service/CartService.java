package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.CartCreateDto;
import com.vn.mobilecity.domain.dto.request.CartUpdateDto;
import com.vn.mobilecity.domain.dto.response.CartDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface CartService {

    List<CartDto> getAll(Integer userId);

    int countTotalItem(Integer userId);

    CartDto create(Integer userId, CartCreateDto cartCreateDto);

    CartDto updateById(Integer userId, Integer id, CartUpdateDto cartUpdateDto);

    CommonResponseDto deleteById(Integer userId, Integer id);

}
