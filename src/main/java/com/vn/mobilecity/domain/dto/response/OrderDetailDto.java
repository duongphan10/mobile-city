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
public class OrderDetailDto extends UserDateAuditingDto {
    private Integer id;
    private String orderId;
    private Integer orderStatusId;
    private String orderStatusName;
    private ProductOptionDto productOptionDto;
    private Integer quantity;
    private Long price;

}
