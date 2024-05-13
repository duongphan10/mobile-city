package com.vn.mobilecity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusConstant {
    WAITING(1, "Đang chờ xác nhận", "Waiting for progressing"),
    CONFIRMED(2, "Đã xác nhận", "Confirmed"),
    DELIVERING(3, "Đang giao hàng", "Delivering"),
    DELIVERED(4, "Đã giao hàng", "Delivered"),
    CANCELLED(5, "Đã hủy", "Cancelled");

    private final Integer id;
    private final String name;
    private final String description;

}
