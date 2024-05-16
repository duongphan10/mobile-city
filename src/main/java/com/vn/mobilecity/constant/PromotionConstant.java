package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionConstant {
    NONE(0, "Không", "Không có khuyến mãi"),
    GIAM_GIA(1, "Giảm giá", "Giảm giá khi mua trực tiếp tại cửa hàng"),
    MOI_RA_MAT(2, "Mới ra mắt", "Khuyến mãi mới ra mắt"),
    GIA_RE_ONLINE(3, "Giá rẻ online", "Khuyến mãi khi thanh toán online"),
    TRA_GOP(4, "Trả góp", "Khuyến mãi khi mua trả góp");

    private final Integer id;
    private final String name;
    private final String description;
}
