package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.domain.dto.request.OrderProductRequestDto;
import com.vn.mobilecity.domain.dto.response.OrderDetailDto;
import com.vn.mobilecity.domain.entity.Order;
import com.vn.mobilecity.domain.entity.OrderDetail;
import com.vn.mobilecity.domain.entity.ProductOption;
import com.vn.mobilecity.domain.entity.User;
import com.vn.mobilecity.domain.mapper.OrderDetailMapper;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.OrderDetailRepository;
import com.vn.mobilecity.repository.OrderRepository;
import com.vn.mobilecity.repository.ProductOptionRepository;
import com.vn.mobilecity.service.OrderDetailService;
import com.vn.mobilecity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductOptionRepository productOptionRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final UserService userService;

    @Override
    public OrderDetailDto getById(Integer id, Integer userId) {
        User user = userService.getById(userId);
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!orderDetail.getCreatedBy().equals(userId) && !user.getRole().getId().equals(RoleConstant.ADMIN.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return orderDetailMapper.mapOrderDetailToOrderDetailDto(orderDetail);
    }

    @Override
    public List<OrderDetailDto> getAllByOrderId(Integer orderId, Integer userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{orderId.toString()}));
        User user = userService.getById(userId);
        if (!order.getCreatedBy().equals(userId) && !user.getRole().getId().equals(RoleConstant.ADMIN.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrder(order);
        return orderDetailMapper.mapOrderDetailsToOrderDetailDtos(orderDetails);
    }

    @Override
    public OrderDetail create(Order order, OrderProductRequestDto orderProductRequestDto) {
        ProductOption productOption = productOptionRepository.findById(orderProductRequestDto.getProductOptionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID,
                        new String[]{orderProductRequestDto.getProductOptionId().toString()}));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProductOption(productOption);
        orderDetail.setQuantity(orderProductRequestDto.getQuantity());
        orderDetail.setPrice(productOption.getPrice());
        orderDetail.setNetPrice(productOption.getNewPrice());
        return orderDetailRepository.save(orderDetail);
    }

}
