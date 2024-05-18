package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.NewsRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NewsDto;

import java.util.List;

public interface NewsService {
    NewsDto getById(Integer id);

    List<NewsDto> getAll(Integer newsTypeId, Boolean status);

    List<NewsDto> getByUser(Integer newsTypeId);

    NewsDto create(NewsRequestDto createDto);

    NewsDto update(Integer id, NewsRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);


}
