package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.CommonConstant;
import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.common.DataMailDto;
import com.vn.mobilecity.domain.dto.request.ForgotPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.LoginRequestDto;
import com.vn.mobilecity.domain.dto.request.TokenRefreshRequestDto;
import com.vn.mobilecity.domain.dto.request.VerifyRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.LoginResponseDto;
import com.vn.mobilecity.domain.dto.response.TokenRefreshResponseDto;
import com.vn.mobilecity.domain.entity.OTP;
import com.vn.mobilecity.domain.entity.User;
import com.vn.mobilecity.exception.InternalServerException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.exception.UnauthorizedException;
import com.vn.mobilecity.repository.OTPRepository;
import com.vn.mobilecity.repository.UserRepository;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.security.jwt.JwtTokenProvider;
import com.vn.mobilecity.service.AuthService;
import com.vn.mobilecity.util.CheckLoginRequest;
import com.vn.mobilecity.util.CodeUtil;
import com.vn.mobilecity.util.SendMailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OTPRepository OTPRepository;
    private final SendMailUtil sendMailUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        try {
            Authentication authentication = null;
            if (CheckLoginRequest.isPhone(request.getAccount())) {
                User user = userRepository.findUserByPhone(request.getAccount()).orElseThrow(
                        () -> new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME));
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
            } else {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword()));
            }
            if (authentication == null) {
                throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
            String refreshToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.TRUE);
            return new LoginResponseDto(accessToken, refreshToken, userPrincipal.getId(), authentication.getAuthorities());
        } catch (InternalAuthenticationServiceException e) {
            throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
        }
    }

    @Override
    public TokenRefreshResponseDto refresh(TokenRefreshRequestDto request) {
        return null;
    }

    @Override
    public CommonResponseDto logout(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return new CommonResponseDto(true, MessageConstant.SUCCESSFULLY_LOGOUT);
    }

    @Override
    public CommonResponseDto forgotPassword(ForgotPasswordRequestDto requestDto) {
        User user = userRepository.findUserByEmail(requestDto.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_WITH_EMAIL, new String[]{requestDto.getEmail()}));

        String verificationCode = CodeUtil.generateCode(CommonConstant.VERIFICATION_C0DE_LENGTH);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(CommonConstant.VERIFICATION_CODE_EXPIRATION_MINUTES);

        OTP otp = OTPRepository.findByUserId(user.getId());
        if (otp != null) {
            otp.setCode(verificationCode);
            otp.setExpirationTime(expirationTime);
        } else {
            otp = new OTP(null, verificationCode, expirationTime, true, user);
        }
        OTPRepository.save(otp);

        try {
            Map<String, Object> props = new HashMap<>();
            props.put("username", user.getUsername());
            props.put("expirationTime", CommonConstant.VERIFICATION_CODE_EXPIRATION_MINUTES);
            props.put("code", verificationCode);
            DataMailDto mail = new DataMailDto(user.getEmail(), MessageConstant.SUBJECT_MAIL_RESET_PASSWORD, null, props);
            sendMailUtil.sendEmailWithHTML(mail, "verify-forgot-password.html");
        } catch (Exception e) {
            throw new InternalServerException("Send mail failed for " + e.getMessage());
        }
        return new CommonResponseDto(true, MessageConstant.VERIFY_FORGOT_PASSWORD + user.getEmail());
    }

    @Override
    public CommonResponseDto verifyForgotPassword(VerifyRequestDto verifyRequestDto) {
        User user = userRepository.findUserByEmail(verifyRequestDto.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_WITH_EMAIL, new String[]{verifyRequestDto.getEmail()}));
        OTP otp = OTPRepository.findByUserId(user.getId());
        if (otp != null) {
            if (!otp.getCode().equals(verifyRequestDto.getVerificationCode()))
                return new CommonResponseDto(false, MessageConstant.VERIFY_FORGOT_PASSWORD_INVALID);
            if (otp.getExpirationTime().isBefore(LocalDateTime.now()) || !otp.getEnabled())
                return new CommonResponseDto(false, MessageConstant.VERIFY_FORGOT_PASSWORD_EXPIRED);

            otp.setEnabled(false);
            OTPRepository.save(otp);

            UserPrincipal userPrincipal = UserPrincipal.create(user);
            String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
            return new CommonResponseDto(true, accessToken);
        }
        return new CommonResponseDto(false, MessageConstant.ERR_VERIFY_FORGOT_PASSWORD);
    }

}
