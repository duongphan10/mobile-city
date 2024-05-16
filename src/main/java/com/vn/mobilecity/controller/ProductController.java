package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class ProductController {
    private final ProductService productService;

    @Tag(name = "product-controller")
    @Operation(summary = "API get product by id")
    @GetMapping(UrlConstant.Product.GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return BaseResponse.success(productService.getById(id));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API get all product")
    @GetMapping(UrlConstant.Product.GET_ALL)
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                                           @RequestParam(name = "promotionId", required = false) Integer promotionId) {
        return BaseResponse.success(productService.getAll(categoryId, promotionId));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API search product")
    @GetMapping(UrlConstant.Product.SEARCH)
    public ResponseEntity<?> searchProduct(@RequestParam(name = "key", required = false, defaultValue = "") String key,
                                           @RequestParam(name = "priceFrom", required = false) Long priceFrom,
                                           @RequestParam(name = "priceTo", required = false) Long priceTo) {
        return BaseResponse.success(productService.search(key, priceFrom, priceTo));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API create product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Product.CREATE)
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductRequestDto productRequestDto) {
        return BaseResponse.success(productService.create(productRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API update product by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Product.UPDATE)
    public ResponseEntity<?> updateProductById(@PathVariable Integer id,
                                               @Valid @ModelAttribute ProductRequestDto productRequestDto) {
        return BaseResponse.success(productService.updateById(id, productRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API delete product by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Product.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        return BaseResponse.success(productService.deleteById(id));
    }

}
