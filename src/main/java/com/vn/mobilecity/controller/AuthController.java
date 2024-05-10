package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.ForgotPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.LoginRequestDto;
import com.vn.mobilecity.domain.dto.request.VerifyRequestDto;
import com.vn.mobilecity.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@RestApiV1
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "API Login")
    @PostMapping(UrlConstant.Auth.LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        return BaseResponse.success(authService.login(request));
    }

    @Operation(summary = "API Logout")
    @GetMapping(UrlConstant.Auth.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication) {
        return BaseResponse.success(authService.logout(request, response, authentication));
    }

    @Operation(summary = "API send verification forgot password")
    @PostMapping(UrlConstant.Auth.SEND_VERIFY)
    public ResponseEntity<?> sendVerificationForgotPassword(@Valid @RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) {
        return BaseResponse.success(authService.forgotPassword(forgotPasswordRequestDto));
    }

    @Operation(summary = "API verify forgot password")
    @PostMapping(value = UrlConstant.Auth.VERIFY)
    public ResponseEntity<?> verifyForgotPassword(@Valid @RequestBody VerifyRequestDto verifyRequestDto) {
        return BaseResponse.success(authService.verifyForgotPassword(verifyRequestDto));
    }

}
