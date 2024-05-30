package com.vn.mobilecity.domain.dto.request;

import com.vn.mobilecity.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreateDto {
//    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
//    private Integer addressId;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String customerName;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String phone;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String address;
    @Valid
    @Size(min = 1, message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private List<OrderProductRequestDto> orderProductRequestDtos;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String note;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Long shippingFee;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Integer paymentTypeId;
}
