package com.vn.mobilecity.domain.dto.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class UserAuditingDto {

    private Integer createdBy;

    private Integer lastModifiedBy;

}
