package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.CheckMomoStatusRequestDto;
import com.vn.mobilecity.domain.dto.request.MomoRequestDto;
import com.vn.mobilecity.service.MomoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestApiV1
public class PaymentController {
    private final MomoService paymentService;

    @Tag(name = "momo create transaction")
    @Operation(summary = "API get all product")
    @PostMapping(UrlConstant.Payment.CREATE)
    public ResponseEntity<?> createPayment(@RequestBody MomoRequestDto requestDto) {
        return paymentService.createPayment(requestDto);
    }

    @Tag(name = "momo create transaction")
    @Operation(summary = "API get all product")
    @PostMapping(UrlConstant.Payment.CHECK)
    public ResponseEntity<?> checkStatusTransaction(@RequestBody CheckMomoStatusRequestDto requestDto) {
        return paymentService.checkStatusTransaction(requestDto);
    }
}
