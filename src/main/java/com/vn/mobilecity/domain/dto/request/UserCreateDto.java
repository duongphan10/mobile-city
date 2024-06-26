package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.RegexConstant;
import com.vn.mobilecity.validator.annotation.ValidPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    @ValidPhone
    private String phone;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    @Email(message = ErrorMessage.INVALID_FORMAT_EMAIL)
    private String email;
    @NotBlank(message = ErrorMessage.INVALID_FORMAT_USERNAME)
    @Pattern(regexp = RegexConstant.USERNAME, message = ErrorMessage.INVALID_FORMAT_USERNAME)
    private String username;
    @NotBlank(message = ErrorMessage.INVALID_FORMAT_PASSWORD)
    @Pattern(regexp = RegexConstant.PASSWORD, message = ErrorMessage.INVALID_FORMAT_PASSWORD)
    private String password;

}
