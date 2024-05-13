package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.SlideRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.SlideDto;

import java.util.List;

public interface SlideService {
    SlideDto getById(Integer id);

    List<SlideDto> getAll(Boolean status);

    List<SlideDto> getSlideByUser();

    SlideDto create(SlideRequestDto createDto);

    SlideDto updateById(Integer id, SlideRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);
}
