package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestApiV1
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Tag(name = "order-detail-controller")
    @Operation(summary = "API get order detail by id")
    @GetMapping(UrlConstant.OrderDetail.GET_BY_ID)
    public ResponseEntity<?> getOrderDetailById(@PathVariable Integer id,
                                                @Parameter(name = "principal", hidden = true)
                                                @CurrentUser UserPrincipal user) {
        return BaseResponse.success(orderDetailService.getById(id, user.getId()));
    }

    @Tag(name = "order-detail-controller")
    @Operation(summary = "API get all order detail by order id")
    @GetMapping(UrlConstant.OrderDetail.GET_ALL)
    public ResponseEntity<?> getAllOrderDetail(@RequestParam(name = "orderId") Integer orderId,
                                               @Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return BaseResponse.success(orderDetailService.getAllByOrderId(orderId, user.getId()));
    }

}
