package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.*;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.pagination.PagingMeta;
import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.request.OrderProductRequestDto;
import com.vn.mobilecity.domain.dto.request.OrderUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.OrderDto;
import com.vn.mobilecity.domain.entity.*;
import com.vn.mobilecity.domain.mapper.OrderMapper;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.InvalidException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.AddressRepository;
import com.vn.mobilecity.repository.OrderRepository;
import com.vn.mobilecity.repository.OrderStatusRepository;
import com.vn.mobilecity.repository.ProductOptionRepository;
import com.vn.mobilecity.service.OrderDetailService;
import com.vn.mobilecity.service.OrderService;
import com.vn.mobilecity.service.UserService;
import com.vn.mobilecity.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductOptionRepository productOptionRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;

    @Override
    public OrderDto getById(Integer id, Integer userId) {
        User user = userService.getById(userId);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!order.getCreatedBy().equals(userId) && !user.getRole().getName().equals(RoleConstant.ADMIN)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public PaginationResponseDto<OrderDto> getAllByUserId(Integer userId, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.ORDER);
        Page<Order> orderPage = orderRepository.getAllByUserId(userId, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.ORDER, orderPage);
        List<OrderDto> orderDtos = orderMapper.mapOrdersToOrderDtos(orderPage.getContent());
        return new PaginationResponseDto<>(meta, orderDtos);
    }

    @Override
    public PaginationResponseDto<OrderDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.ORDER);
        Page<Order> orderPage = orderRepository.getAll(paginationFullRequestDto.getKeyword(), pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.ORDER, orderPage);
        List<OrderDto> orderDtos = orderMapper.mapOrdersToOrderDtos(orderPage.getContent());
        return new PaginationResponseDto<>(meta, orderDtos);
    }

    @Override
    public OrderDto create(Integer userId, OrderCreateDto orderCreateDto) {
        Address address = addressRepository.findById(orderCreateDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getAddressId().toString()}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new InvalidException(ErrorMessage.Order.ERR_INVALID_ADDRESS);
        }

        long originalPrice = 0L;
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            ProductOption productOption = productOptionRepository.findById(requestDto.getProductOptionId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{requestDto.getProductOptionId().toString()}));
            if (!productOption.getStatus() || productOption.getQuantity() < requestDto.getQuantity()) {
                throw new InvalidException(ErrorMessage.Order.ERR_INVALID_PRODUCT_OPTION);
            }
            originalPrice += requestDto.getQuantity() * productOption.getPrice();
        }
        long totalPrice = originalPrice + orderCreateDto.getShippingFee();
        Order order = orderMapper.mapOrderCreateDtoToOrder(orderCreateDto);
        order.setCustomerName(address.getCustomerName());
        order.setPhone(address.getPhone());
        order.setAddress(address.getAddress());
        order.setOriginalPrice(originalPrice);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(orderStatusRepository.getById(StatusConstant.PENDING.getId()));
        orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            orderDetails.add(orderDetailService.create(order, requestDto));
        }
        order.setOrderDetails(orderDetails);

        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public OrderDto updateById(Integer id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (orderUpdateDto.getStatusId() > StatusConstant.CANCELLED.getId()
                || orderUpdateDto.getStatusId() <= StatusConstant.PENDING.getId()) {
            throw new InvalidException(ErrorMessage.OrderDetail.ERR_INVALID_STATUS_UPDATE);
        }
        if (order.getOrderStatus().getId().equals(StatusConstant.DELIVERED.getId())
                || order.getOrderStatus().getId().equals(StatusConstant.CANCELLED.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        order.setOrderStatus(orderStatusRepository.getById(orderUpdateDto.getStatusId()));
        if (orderUpdateDto.getStatusId().equals(StatusConstant.DELIVERED.getId())) {
            order.setPaymentStatus(true);
        }
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        orderRepository.delete(order);
        return new CommonResponseDto(true, MessageConstant.DELETE_ORDER_SUCCESSFULLY);
    }

}
