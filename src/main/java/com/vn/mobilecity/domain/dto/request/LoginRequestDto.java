package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String account;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String password;

}
