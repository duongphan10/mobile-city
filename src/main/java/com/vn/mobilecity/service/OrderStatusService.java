package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.response.OrderStatusDto;

import java.util.List;

public interface OrderStatusService {

    OrderStatusDto getById(Integer id);

    List<OrderStatusDto> getAll();
}
