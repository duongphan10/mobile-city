package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.response.OrderDto;
import com.vn.mobilecity.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    Order mapOrderCreateDtoToOrder(OrderCreateDto orderCreateDto);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "statusId", source = "orderStatus.id"),
            @Mapping(target = "statusName", source = "orderStatus.name"),
            @Mapping(target = "paymentTypeId", source = "paymentType.id"),
            @Mapping(target = "paymentTypeName", source = "paymentType.name"),
    })
    OrderDto mapOrderToOrderDto(Order order);

    List<OrderDto> mapOrdersToOrderDtos(List<Order> orders);

}
