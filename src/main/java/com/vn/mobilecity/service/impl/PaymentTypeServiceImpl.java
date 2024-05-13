package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.domain.dto.response.PaymentTypeDto;
import com.vn.mobilecity.domain.entity.PaymentType;
import com.vn.mobilecity.domain.mapper.PaymentTypeMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.PaymentTypeRepository;
import com.vn.mobilecity.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    @Override
    public PaymentTypeDto getById(Integer id) {
        PaymentType paymentType = paymentTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PaymentType.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return paymentTypeMapper.mapPaymentTypeToPaymentTypeDto(paymentType);
    }

    @Override
    public List<PaymentTypeDto> getAll() {
        List<PaymentType> paymentTypes = paymentTypeRepository.findAll();
        return paymentTypeMapper.mapPaymentTypesToPaymentTypeDtos(paymentTypes);
    }
}
