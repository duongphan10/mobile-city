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
public class ReviewDto extends UserDateAuditingDto {
    private Integer id;
    private Integer star;
    private String description;
    private Integer orderDetailId;
    private List<ReviewFileDto> fileDtos;

}
