package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.AddressRequestDto;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class AddressController {
    private final AddressService addressService;

    @Tag(name = "address-controller")
    @Operation(summary = "API get address by id")
    @GetMapping(UrlConstant.Address.GET_BY_ID)
    public ResponseEntity<?> getAddressById(@PathVariable Integer id,
                                            @Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.getById(id, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API get all address current user")
    @GetMapping(UrlConstant.Address.GET_ALL)
    public ResponseEntity<?> getAllAddress(@Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.getAllByUserId(user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API get address default by user id")
    @GetMapping(UrlConstant.Address.GET_DEFAULT)
    public ResponseEntity<?> getAddressDefault(@Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.getDefault(user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API create address")
    @PostMapping(UrlConstant.Address.CREATE)
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto,
                                           @Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.create(addressRequestDto, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API update address")
    @PatchMapping(UrlConstant.Address.UPDATE_BY_ID)
    public ResponseEntity<?> updateAddressById(@PathVariable Integer id,
                                               @Valid @RequestBody AddressRequestDto addressRequestDto,
                                               @Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.updateById(id, addressRequestDto, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API change address default by id")
    @PatchMapping(UrlConstant.Address.CHANGE_DEFAULT)
    public ResponseEntity<?> changeAddressDefaultById(@PathVariable Integer id,
                                                      @Parameter(name = "principal", hidden = true)
                                                      @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.changeDefaultById(id, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API delete address by id")
    @DeleteMapping(UrlConstant.Address.DELETE_BY_ID)
    public ResponseEntity<?> deleteAddressById(@PathVariable Integer id,
                                               @Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return BaseResponse.success(addressService.deleteById(id, user.getId()));
    }

}
