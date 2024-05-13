package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleConstant {
    ADMIN(1, "ROLE_ADMIN", null),
    USER(2, "ROLE_USER", null),
    SUPPORT(3, "ROLE_SUPPORT", null);
    private final Integer id;
    private final String name;
    private final String description;
}
