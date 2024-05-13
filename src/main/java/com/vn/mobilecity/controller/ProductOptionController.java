package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.ProductOptionCreateDto;
import com.vn.mobilecity.domain.dto.request.ProductOptionUpdateDto;
import com.vn.mobilecity.service.ProductOptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    @Tag(name = "product-option-controller")
    @Operation(summary = "API get product option by id")
    @GetMapping(UrlConstant.ProductOption.GET_OPTION_BY_ID)
    public ResponseEntity<?> getProductOptionById(@PathVariable Integer id) {
        return BaseResponse.success(productOptionService.getById(id));
    }

    @Tag(name = "product-option-controller")
    @Operation(summary = "API get all option by product")
    @GetMapping(UrlConstant.ProductOption.GET_ALL_OPTION)
    public ResponseEntity<?> getAllProductOption(@RequestParam(name = "productId", required = false) Integer productId) {
        return BaseResponse.success(productOptionService.getAllProductOption(productId));
    }

    @Tag(name = "product-option-controller")
    @Operation(summary = "API create option of product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.ProductOption.CREATE_OPTION)
    public ResponseEntity<?> createProductOption(@Valid @ModelAttribute ProductOptionCreateDto productOptionCreateDto) {
        return BaseResponse.success(productOptionService.create(productOptionCreateDto));
    }

    @Tag(name = "product-option-controller")
    @Operation(summary = "API update product option by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.ProductOption.UPDATE_OPTION)
    public ResponseEntity<?> updateProductOptionById(@PathVariable Integer id,
                                                     @Valid @ModelAttribute ProductOptionUpdateDto productOptionUpdateDto) {
        return BaseResponse.success(productOptionService.updateById(id, productOptionUpdateDto));
    }

    @Tag(name = "product-option-controller")
    @Operation(summary = "API delete product option by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.ProductOption.DELETE_OPTION)
    public ResponseEntity<?> deleteProductOptionById(@PathVariable Integer id) {
        return BaseResponse.success(productOptionService.deleteById(id));
    }

}
