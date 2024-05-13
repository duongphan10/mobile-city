package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.response.OrderStatusDto;
import com.vn.mobilecity.domain.entity.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderStatusMapper {

    OrderStatusDto mapOrderStatusToOrderStatusDto(OrderStatus orderStatus);

    List<OrderStatusDto> mapOrderStatusesToOrderStatusDtos(List<OrderStatus> orderStatuses);

}
