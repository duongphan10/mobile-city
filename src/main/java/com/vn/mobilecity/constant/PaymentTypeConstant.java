package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeConstant {
    OFFLINE(1, "Thanh toán trực tiếp", "Thanh toán trực tiếp tại cửa hàng"),
    MOMO(2, "MoMo", "Thanh toán qua MoMo"),
    VNPAY(3, "ZaloPay", "Thanh toán qua ZaloPay"),
    PAYOS(4, "PayOS", "Thanh toán qua PayOS"),
    TTKNH(5, "Thanh toán khi nhận hàng", "Thanh toán khi nhận hàng");

    private final Integer id;
    private final String name;
    private final String description;

}
