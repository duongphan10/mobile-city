package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.domain.dto.response.OrderStatusDto;
import com.vn.mobilecity.domain.entity.OrderStatus;
import com.vn.mobilecity.domain.mapper.OrderStatusMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.OrderStatusRepository;
import com.vn.mobilecity.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public OrderStatusDto getById(Integer id) {
        OrderStatus orderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.OrderStatus.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return orderStatusMapper.mapOrderStatusToOrderStatusDto(orderStatus);
    }

    @Override
    public List<OrderStatusDto> getAll() {
        List<OrderStatus> orderStatuses = orderStatusRepository.findAll();
        return orderStatusMapper.mapOrderStatusesToOrderStatusDtos(orderStatuses);
    }
}
