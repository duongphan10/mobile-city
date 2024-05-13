package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.ReviewCreateDto;
import com.vn.mobilecity.domain.dto.request.ReviewUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto getById(Integer id);

    List<ReviewDto> search(Integer productId, Integer star);

    double countStar(Integer productId);

    ReviewDto create(Integer userId, ReviewCreateDto reviewCreateDto);

    ReviewDto updateById(Integer userId, Integer id, ReviewUpdateDto reviewUpdateDto);

    CommonResponseDto deleteById(Integer userId, Integer id);

}
