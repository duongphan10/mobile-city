package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.ReviewCreateDto;
import com.vn.mobilecity.domain.dto.request.ReviewUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ReviewDto;

public interface ReviewService {

    ReviewDto getById(Integer id);

    PaginationResponseDto<ReviewDto> search(Integer productId, Integer star, PaginationFullRequestDto paginationFullRequestDto);

    ReviewDto create(Integer userId, ReviewCreateDto reviewCreateDto);

    ReviewDto updateById(Integer userId, Integer id, ReviewUpdateDto reviewUpdateDto);

    CommonResponseDto deleteById(Integer userId, Integer id);

}
