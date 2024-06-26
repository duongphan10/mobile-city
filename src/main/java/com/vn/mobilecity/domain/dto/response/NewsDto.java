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
public class NewsDto extends UserDateAuditingDto {
    private Integer id;
    private String title;
    private String avatar;
    private String summary;
    private String content;
    private Boolean status;
    private Long view;
    private String newsTypeId;
    private String newsTypeName;
}
