package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.request.NewsRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NewsDto;

import java.util.List;

public interface NewsService {
    NewsDto getById(Integer id);

    List<NewsDto> getAll(Boolean status);

    List<NewsDto> getByUser();

    NewsDto create(NewsRequestDto createDto);

    NewsDto update(Integer id, NewsRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);


}
