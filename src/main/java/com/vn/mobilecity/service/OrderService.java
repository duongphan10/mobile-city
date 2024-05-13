package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.request.OrderUpdateDto;
import com.vn.mobilecity.domain.dto.response.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto getById(Integer id, Integer userId);

    List<OrderDto> getAllByUserId(Integer userId, Integer status);

    List<OrderDto> getAll(Integer status, Integer type);

    OrderDto create(Integer userId, OrderCreateDto orderCreateDto);

    OrderDto updateById(Integer userId, Integer id, OrderUpdateDto orderUpdateDto);

}
