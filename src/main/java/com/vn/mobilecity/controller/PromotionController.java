package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.PromotionRequestDto;
import com.vn.mobilecity.service.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class PromotionController {
    private final PromotionService promotionService;

    @Tag(name = "promotion-controller")
    @Operation(summary = "API get promotion by id")
    @GetMapping(UrlConstant.Promotion.GET_BY_ID)
    public ResponseEntity<?> getPromotionById(@PathVariable Integer id) {
        return BaseResponse.success(promotionService.getById(id));
    }

    @Tag(name = "promotion-controller")
    @Operation(summary = "API get all promotion")
    @GetMapping(UrlConstant.Promotion.GET_ALL)
    public ResponseEntity<?> getAllPromotion() {
        return BaseResponse.success(promotionService.getAll());
    }

    @Tag(name = "promotion-controller")
    @Operation(summary = "API create promotion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Promotion.CREATE)
    public ResponseEntity<?> createPromotion(@Valid @RequestBody PromotionRequestDto requestDto) {
        return BaseResponse.success(promotionService.create(requestDto));
    }

    @Tag(name = "promotion-controller")
    @Operation(summary = "API update promotion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Promotion.UPDATE)
    public ResponseEntity<?> updatePromotion(@PathVariable Integer id, @Valid @RequestBody PromotionRequestDto requestDto) {
        return BaseResponse.success(promotionService.update(id, requestDto));
    }

    @Tag(name = "promotion-controller")
    @Operation(summary = "API delete promotion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Promotion.DELETE)
    public ResponseEntity<?> deletePromotion(@PathVariable Integer id) {
        return BaseResponse.success(promotionService.deleteById(id));
    }
}
