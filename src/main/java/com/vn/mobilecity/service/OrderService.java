package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.request.OrderUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.OrderDto;

public interface OrderService {

    OrderDto getById(Integer id, Integer userId);

    PaginationResponseDto<OrderDto> getAllByUserId(Integer userId, PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<OrderDto> getAll(PaginationFullRequestDto paginationFullRequestDto);

    OrderDto create(Integer userId, OrderCreateDto orderCreateDto);

    OrderDto updateById(Integer id, OrderUpdateDto orderUpdateDto);

    CommonResponseDto deleteById(Integer id);

}
