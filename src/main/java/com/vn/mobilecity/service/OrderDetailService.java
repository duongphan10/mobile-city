package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.OrderProductRequestDto;
import com.vn.mobilecity.domain.dto.response.OrderDetailDto;
import com.vn.mobilecity.domain.entity.Order;
import com.vn.mobilecity.domain.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDto getById(Integer id, Integer userId);

    List<OrderDetailDto> getAllByOrderId(Integer orderId, Integer userId);

    OrderDetail create(Order order, OrderProductRequestDto orderProductRequestDto);

}
