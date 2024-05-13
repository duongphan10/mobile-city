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
public class ProductOptionDto extends UserDateAuditingDto {
    private Integer productId;
    private String productName;
    private Integer id;
    private Integer ram;
    private Integer storageCapacity;
    private String color;
    private String image;
    private Long price;
    private Integer quantity;
    private Boolean status;

}
