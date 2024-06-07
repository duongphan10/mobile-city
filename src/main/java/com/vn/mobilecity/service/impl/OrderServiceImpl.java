package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.config.NotificationConfig;
import com.vn.mobilecity.constant.*;
import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.request.OrderProductRequestDto;
import com.vn.mobilecity.domain.dto.request.OrderUpdateDto;
import com.vn.mobilecity.domain.dto.response.OrderDto;
import com.vn.mobilecity.domain.entity.*;
import com.vn.mobilecity.domain.mapper.OrderMapper;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.InvalidException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.proactive.alert.AsyncService;
import com.vn.mobilecity.repository.*;
import com.vn.mobilecity.service.OrderDetailService;
import com.vn.mobilecity.service.OrderService;
import com.vn.mobilecity.service.UserService;
import com.vn.mobilecity.util.CodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final ProductOptionRepository productOptionRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;
    private final AsyncService asyncService;
    private final NotificationConfig notificationConfig;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant.PATTERN_DATE_TIME_COMMON);

    @Override
    public OrderDto getById(Integer id, Integer userId) {
        User user = userService.getById(userId);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!order.getCreatedBy().equals(userId) && !user.getRole().getId().equals(RoleConstant.ADMIN.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllByUserId(Integer userId, Integer statusId) {
        List<Order> orders = orderRepository.getAllByUserId(userId, statusId);
        return orderMapper.mapOrdersToOrderDtos(orders);
    }

    @Override
    public List<OrderDto> getAll(Integer statusId, Integer paymentTypeId) {
        List<Order> orders = orderRepository.getAll(statusId, paymentTypeId);
        return orderMapper.mapOrdersToOrderDtos(orders);
    }

    @Override
    public OrderDto create(Integer userId, OrderCreateDto orderCreateDto) {
        User user = userService.getById(userId);
//        Address address = addressRepository.findById(orderCreateDto.getAddressId())
//                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getAddressId().toString()}));
//        if (!address.getCreatedBy().equals(userId)) {
//            throw new InvalidException(ErrorMessage.Order.ERR_INVALID_ADDRESS);
//        }
        PaymentType paymentType = paymentTypeRepository.findById(orderCreateDto.getPaymentTypeId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PaymentType.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getPaymentTypeId().toString()}));


        long originalPrice = 0L, netPriceTotal = 0L;
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            ProductOption productOption = productOptionRepository.findById(requestDto.getProductOptionId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{requestDto.getProductOptionId().toString()}));
            if (!productOption.getProduct().getStatus() || !productOption.getStatus() || productOption.getQuantity() < requestDto.getQuantity()) {
                throw new InvalidException(ErrorMessage.Order.ERR_INVALID_PRODUCT_OPTION);
            }
            originalPrice += requestDto.getQuantity() * productOption.getPrice();
            netPriceTotal += requestDto.getQuantity() * productOption.getNewPrice();
        }
        netPriceTotal += orderCreateDto.getShippingFee();
        Order order = orderMapper.mapOrderCreateDtoToOrder(orderCreateDto);
        order.setUser(user);
        order.setCustomerName(orderCreateDto.getCustomerName());
        order.setPhone(orderCreateDto.getPhone());
        order.setAddress(orderCreateDto.getAddress());
        order.setOriginalPrice(originalPrice);
        order.setNetPriceTotal(netPriceTotal);
        OrderStatus orderStatus = orderStatusRepository.getById(OrderStatusConstant.WAITING.getId());
        order.setOrderStatus(orderStatus);
        order.setPaymentType(paymentType);
        order.setPaymentStatus(orderCreateDto.getPaymentTypeId().equals(PaymentTypeConstant.MOMO.getId())
                || orderCreateDto.getPaymentTypeId().equals(PaymentTypeConstant.VNPAY.getId())
                || orderCreateDto.getPaymentTypeId().equals(PaymentTypeConstant.PAYOS.getId()));
        orderRepository.save(order);
        order.setOrderCode(CodeUtil.generateCodeOrder(order.getId(), CommonConstant.ORDER_C0DE_LENGTH));
        orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            orderDetails.add(orderDetailService.create(order, requestDto));
        }
        order.setOrderDetails(orderDetails);

        String message =
                "Mã đơn hàng: " + order.getId() +
                "\nTrạng thái: " + orderStatus.getName() +
                "\nLoại thanh toán: " + order.getPaymentType().getName() +
                "\nTình trạng: " + (order.getPaymentStatus() ? "Đã thanh toán" : "Chưa thanh toán") +
                "\nNgười đặt: " + order.getUser().getUsername() + " (" + order.getUser().getPhone() + " - " + order.getUser().getEmail() + ")" +
                "\nNgày đặt: " + order.getCreatedDate().format(formatter);
        asyncService.sendTelegramMessage(notificationConfig.ORDER, message);

        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public OrderDto updateById(Integer userId, Integer id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        OrderStatus oldStatus = order.getOrderStatus();
        if (orderUpdateDto.getStatusId() > OrderStatusConstant.CANCELLED.getId()
                || orderUpdateDto.getStatusId() < OrderStatusConstant.WAITING.getId()) {
            throw new InvalidException(ErrorMessage.Order.ERR_INVALID_STATUS_UPDATE);
        }
        if (oldStatus.getId().equals(OrderStatusConstant.DELIVERED.getId())
                || oldStatus.getId().equals(OrderStatusConstant.CANCELLED.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        OrderStatus orderStatus = orderStatusRepository.getById(orderUpdateDto.getStatusId());
        order.setOrderStatus(orderStatus);
        if (orderUpdateDto.getStatusId().equals(OrderStatusConstant.DELIVERED.getId())) {
            order.setPaymentStatus(true);
        }
        orderRepository.save(order);

        // send message telegram
        if (!oldStatus.getId().equals(orderStatus.getId())) {
            User user = userService.getById(userId);
            String message =
                    "Mã đơn hàng: " + order.getId() +
                    "\nTrạng thái mới: " + orderStatus.getName() +
                    "\nTrạng thái cũ: " + oldStatus.getName() +
                    "\nLoại thanh toán: " + order.getPaymentType().getName() +
                    "\nTình trạng: " + (order.getPaymentStatus() ? "Đã thanh toán" : "Chưa thanh toán") +
                    "\nNgười đặt: " + order.getUser().getUsername() + " (" + order.getUser().getPhone() + " - " + order.getUser().getEmail() + ")" +
                    "\nNgày đặt: " + order.getCreatedDate().format(formatter) +
                    "\nNgười cập nhật: " + user.getUsername();
            asyncService.sendTelegramMessage(notificationConfig.STATUS, message);
        }

        return orderMapper.mapOrderToOrderDto(order);
    }

}
