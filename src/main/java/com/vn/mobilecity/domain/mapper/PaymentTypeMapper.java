package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.response.PaymentTypeDto;
import com.vn.mobilecity.domain.entity.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentTypeMapper {

    PaymentTypeDto mapPaymentTypeToPaymentTypeDto(PaymentType paymentType);

    List<PaymentTypeDto> mapPaymentTypesToPaymentTypeDtos(List<PaymentType> paymentTypes);

}
