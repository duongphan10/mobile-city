package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.NewsTypeRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NewsTypeDto;

import java.util.List;

public interface NewsTypeService {

    NewsTypeDto getById(Integer id);

    List<NewsTypeDto> getAll();

    NewsTypeDto create(NewsTypeRequestDto createDto);

    NewsTypeDto update(Integer id, NewsTypeRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);

}
