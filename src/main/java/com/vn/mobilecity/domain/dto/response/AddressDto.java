package com.vn.mobilecity.domain.dto.response;

import com.vn.mobilecity.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto extends UserDateAuditingDto {
    private Integer id;
    private String customerName;
    private String phone;
    private String address;
    private Boolean type;
    private Boolean addressDefault;
    private Integer userId;
}
