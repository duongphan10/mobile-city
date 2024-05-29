package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeConstant {
    OFFLINE(1, "Thanh toán trực tiếp", "Thanh toán trực tiếp tại cửa hàng"),
    MOMO(2, "MoMo", "Thanh toán qua MoMo"),
    VNPAY(3, "VNPay", "Thanh toán qua VNPay"),
    TTKNH(4, "Thanh toán khi nhận hàng", "Thanh toán khi nhận hàng");

    private final Integer id;
    private final String name;
    private final String description;

}
