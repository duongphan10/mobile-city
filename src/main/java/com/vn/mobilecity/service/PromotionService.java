package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.PromotionRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.PromotionDto;

import java.util.List;

public interface PromotionService {

    PromotionDto getById(Integer id);

    List<PromotionDto> getAll();

    PromotionDto create(PromotionRequestDto createDto);

    PromotionDto update(Integer id, PromotionRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);

}
