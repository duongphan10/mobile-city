package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.ForgotPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.LoginRequestDto;
import com.vn.mobilecity.domain.dto.request.TokenRefreshRequestDto;
import com.vn.mobilecity.domain.dto.request.VerifyRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.LoginResponseDto;
import com.vn.mobilecity.domain.dto.response.TokenRefreshResponseDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);

    TokenRefreshResponseDto refresh(TokenRefreshRequestDto request);

    CommonResponseDto logout(HttpServletRequest request,
                             HttpServletResponse response, Authentication authentication);

    CommonResponseDto forgotPassword(ForgotPasswordRequestDto requestDto);

    CommonResponseDto verifyForgotPassword(VerifyRequestDto verifyRequestDto);

}
