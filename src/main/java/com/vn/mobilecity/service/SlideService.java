package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.SlideRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.SlideDto;

public interface SlideService {
    SlideDto getById(Integer id);

    PaginationResponseDto<SlideDto> getAll(PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<SlideDto> getByStatus(PaginationFullRequestDto paginationFullRequestDto, Boolean status);

    SlideDto create(SlideRequestDto createDto);

    SlideDto update(Integer id, SlideRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);
}
