package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeConstant {
    OFFLINE(0, "Thanh toán trực tiếp", "Thanh toán trực tiếp tại cửa hàng"),
    MOMO(1, "MoMo", "Thanh toán qua MoMo"),
    VNPAY(2, "VNPay", "Thanh toán qua VNPay"),
    TTKNH(3, "Thanh toán khi nhận hàng", "Thanh toán khi nhận hàng");

    private final Integer id;
    private final String name;
    private final String description;

}
