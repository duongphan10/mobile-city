package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.ChangePasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.NewPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.UserCreateDto;
import com.vn.mobilecity.domain.dto.request.UserUpdateDto;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class UserController {

    private final UserService userService;

    @Tag(name = "user-controller")
    @Operation(summary = "API get user")
    @GetMapping(UrlConstant.User.GET_BY_ID)
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return BaseResponse.success(userService.getUserById(id));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API get current user login")
    @GetMapping(UrlConstant.User.GET_CURRENT_USER)
    public ResponseEntity<?> getCurrentUser(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return BaseResponse.success(userService.getCurrentUser(user.getId()));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API get all user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.User.GET_ALL)
    public ResponseEntity<?> getAllUser() {
        return BaseResponse.success(userService.getAll());
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API create user")
    @PostMapping(UrlConstant.User.CREATE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return BaseResponse.success(userService.create(userCreateDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API update my profile")
    @PatchMapping(UrlConstant.User.UPDATE)
    public ResponseEntity<?> updateMyProfile(@Parameter(name = "principal", hidden = true)
                                             @CurrentUser UserPrincipal user,
                                             @Valid @ModelAttribute UserUpdateDto userUpdateDto) {
        return BaseResponse.success(userService.update(user.getId(), userUpdateDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API change password")
    @PatchMapping(UrlConstant.User.CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user,
                                            @Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return BaseResponse.success(userService.changePassword(user.getId(), changePasswordRequestDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API create new password")
    @PatchMapping(UrlConstant.User.CREATE_NEW_PASSWORD)
    public ResponseEntity<?> createNewPassword(@Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user,
                                               @Valid @RequestBody NewPasswordRequestDto newPasswordRequestDto) {
        return BaseResponse.success(userService.createNewPassword(user.getId(), newPasswordRequestDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API delete user")
    @DeleteMapping(UrlConstant.User.DELETE)
    public ResponseEntity<?> deleteUserById(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return BaseResponse.success(userService.deleteById(user.getId()));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API delete user by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.User.DELETE_BY_ID)
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        return BaseResponse.success(userService.deleteById(id));
    }

}
