package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestApiV1
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @Tag(name = "order-status-controller")
    @Operation(summary = "API get order status by id")
    @GetMapping(UrlConstant.OrderStatus.GET_BY_ID)
    public ResponseEntity<?> getOrderStatusById(@PathVariable Integer id) {
        return BaseResponse.success(orderStatusService.getById(id));
    }

    @Tag(name = "order-status-controller")
    @Operation(summary = "API get all order status")
    @GetMapping(UrlConstant.OrderStatus.GET_ALL)
    public ResponseEntity<?> getAllOrderStatus() {
        return BaseResponse.success(orderStatusService.getAll());
    }
}
