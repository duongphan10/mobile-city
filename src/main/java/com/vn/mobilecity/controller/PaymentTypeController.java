package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestApiV1
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    @Tag(name = "payment-type-controller")
    @Operation(summary = "API get payment type by id")
    @GetMapping(UrlConstant.PaymentType.GET_BY_ID)
    public ResponseEntity<?> getPaymentTypeById(@PathVariable Integer id) {
        return BaseResponse.success(paymentTypeService.getById(id));
    }

    @Tag(name = "payment-type-controller")
    @Operation(summary = "API get all payment type")
    @GetMapping(UrlConstant.PaymentType.GET_ALL)
    public ResponseEntity<?> getAllPaymentType() {
        return BaseResponse.success(paymentTypeService.getAll());
    }
}
