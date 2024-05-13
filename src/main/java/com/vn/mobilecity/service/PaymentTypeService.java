package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.response.PaymentTypeDto;

import java.util.List;

public interface PaymentTypeService {

    PaymentTypeDto getById(Integer id);

    List<PaymentTypeDto> getAll();
}
