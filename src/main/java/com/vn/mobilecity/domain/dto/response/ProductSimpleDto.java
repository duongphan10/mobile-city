package com.vn.mobilecity.domain.dto.response;

import com.vn.mobilecity.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSimpleDto extends UserDateAuditingDto {
    private Integer id;
    private String name;
    private String avatar;

    private String screenTechnology;
    private String screenResolution;
    private String widescreen;

    private Integer categoryId;
    private String categoryName;

    private List<ProductOptionDto> productOptionDtos;
}
