package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.RegexConstant;
import com.vn.mobilecity.validator.annotation.ValidDate;
import com.vn.mobilecity.validator.annotation.ValidFileImage;
import com.vn.mobilecity.validator.annotation.ValidPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
    private String fullName;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    @Pattern(regexp = RegexConstant.USERNAME, message = ErrorMessage.INVALID_FORMAT_USERNAME)
    private String username;
    private Boolean gender;
    @ValidDate
    private String birthday;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    @ValidPhone
    private String phone;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    @Email(message = ErrorMessage.INVALID_FORMAT_EMAIL)
    private String email;
    @ValidFileImage
    private MultipartFile avatar;

}
