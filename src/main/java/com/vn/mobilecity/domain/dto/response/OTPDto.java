package com.vn.mobilecity.domain.dto.response;

import com.vn.mobilecity.domain.dto.common.DateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OTPDto extends DateAuditingDto {
    private Integer id;
    private String verificationCode;
    private LocalDateTime expirationTime;
    private Boolean enabled;
    private String email;
    private Integer userId;

}
