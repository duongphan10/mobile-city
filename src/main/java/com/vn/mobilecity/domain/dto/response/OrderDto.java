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
public class OrderDto extends UserDateAuditingDto {
    private Integer id;
    private String userId;
    private String orderCode;
    private String customerName;
    private String phone;
    private String address;
    private String note;
    private Long shippingFee;
    private Long originalPrice;
    private Long netPriceTotal;
    private Integer orderStatusId;
    private String orderStatusName;
    private Boolean paymentStatus;
    private Integer paymentTypeId;
    private String paymentTypeName;

}
