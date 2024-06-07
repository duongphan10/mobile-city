package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.CheckMomoStatusRequestDto;
import com.vn.mobilecity.domain.dto.request.MomoRequestDto;
import org.springframework.http.ResponseEntity;

public interface MomoService {
    ResponseEntity<?> createPayment(MomoRequestDto requestDto);
    ResponseEntity<?> checkStatusTransaction(CheckMomoStatusRequestDto requestDto);
}
