package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.request.OrderCreateDto;
import com.vn.mobilecity.domain.dto.request.OrderUpdateDto;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class OrderController {
    private final OrderService orderService;

    @Tag(name = "order-controller")
    @Operation(summary = "API get order by id")
    @GetMapping(UrlConstant.Order.GET_BY_ID)
    public ResponseEntity<?> getOrderById(@PathVariable Integer id,
                                          @Parameter(name = "principal", hidden = true)
                                          @CurrentUser UserPrincipal user) {
        return BaseResponse.success(orderService.getById(id, user.getId()));
    }

    @Tag(name = "order-controller")
    @Operation(summary = "API get all order")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Order.GET_ALL)
    public ResponseEntity<?> getAllOrder(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return BaseResponse.success(orderService.getAll(paginationFullRequestDto));
    }

    @Tag(name = "order-controller")
    @Operation(summary = "API get all order")
    @GetMapping(UrlConstant.Order.GET_MY_ALL)
    public ResponseEntity<?> getMyOrder(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto,
                                        @Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user) {
        return BaseResponse.success(orderService.getAllByUserId(user.getId(), paginationFullRequestDto));
    }

    @Tag(name = "order-controller")
    @Operation(summary = "API create order")
    @PostMapping(UrlConstant.Order.CREATE)
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderCreateDto orderCreateDto,
                                         @Parameter(name = "principal", hidden = true)
                                         @CurrentUser UserPrincipal user) {
        return BaseResponse.success(orderService.create(user.getId(), orderCreateDto));
    }

    @Tag(name = "order-controller")
    @Operation(summary = "API update order by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Order.UPDATE)
    public ResponseEntity<?> updateOrderById(@PathVariable Integer id,
                                             @Valid @RequestBody OrderUpdateDto orderUpdateDto) {
        return BaseResponse.success(orderService.updateById(id, orderUpdateDto));
    }

    @Tag(name = "order-controller")
    @Operation(summary = "API delete order by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Order.DELETE)
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer id) {
        return BaseResponse.success(orderService.deleteById(id));
    }

}
