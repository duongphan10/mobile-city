package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.request.CartCreateDto;
import com.vn.mobilecity.domain.dto.request.CartUpdateDto;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class CartController {

    private final CartService cartService;

    @Tag(name = "cart-controller")
    @Operation(summary = "API get all item of cart")
    @GetMapping(UrlConstant.Cart.GET_ALL)
    public ResponseEntity<?> getAllCart(@Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return BaseResponse.success(cartService.getAll(user.getId(), paginationFullRequestDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API get number item in cart")
    @GetMapping(UrlConstant.Cart.GET_NUMBER_OF_ITEM)
    public ResponseEntity<?> getNumberItem(@Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return BaseResponse.success(cartService.getNumberItem(user.getId()));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API add item to cart")
    @PostMapping(UrlConstant.Cart.CREATE)
    public ResponseEntity<?> createCart(@Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @RequestBody CartCreateDto cartCreateDto) {
        return BaseResponse.success(cartService.create(user.getId(), cartCreateDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API update item in cart")
    @PatchMapping(UrlConstant.Cart.UPDATE)
    public ResponseEntity<?> updateCart(@PathVariable Integer id,
                                        @Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @RequestBody CartUpdateDto cartUpdateDto) {
        return BaseResponse.success(cartService.updateById(user.getId(), id, cartUpdateDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API delete item in cart")
    @DeleteMapping(UrlConstant.Cart.DELETE)
    public ResponseEntity<?> deleteCart(@PathVariable Integer id,
                                        @Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user) {
        return BaseResponse.success(cartService.deleteById(user.getId(), id));
    }

}
