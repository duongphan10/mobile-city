package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeConstant {
    MOMO(1, "MoMo", "Thanh toán MoMo"),
    VNPAY(2, "VNPay", "Thanh toán VNPay"),
    OFFLINE(3, "Thanh toán khi nhận hàng", "Thanh toán khi nhận hàng");

    private final Integer id;
    private final String name;
    private final String description;

}
