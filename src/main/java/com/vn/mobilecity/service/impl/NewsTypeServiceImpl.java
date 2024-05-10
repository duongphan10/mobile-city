package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.NewsTypeRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NewsTypeDto;
import com.vn.mobilecity.domain.entity.NewsType;
import com.vn.mobilecity.domain.mapper.NewsTypeMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.NewsTypeRepository;
import com.vn.mobilecity.service.NewsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsTypeServiceImpl implements NewsTypeService {
    private final NewsTypeRepository newsTypeRepository;
    private final NewsTypeMapper newsTypeMapper;

    @Override
    public NewsTypeDto getById(Integer id) {
        NewsType newsType = newsTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NewsType.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return newsTypeMapper.mapNewsTypeToNewsTypeDto(newsType);
    }


    @Override
    public List<NewsTypeDto> getAll() {
        List<NewsType> newsTypes = newsTypeRepository.getAll();
        return newsTypeMapper.mapNewsTypesToNewsTypeDtos(newsTypes);
    }

    @Override
    public NewsTypeDto create(NewsTypeRequestDto requestDto) {
        NewsType newsType = newsTypeMapper.mapNewsTypeRequestDtoToNewsType(requestDto);
        newsTypeRepository.save(newsType);
        return newsTypeMapper.mapNewsTypeToNewsTypeDto(newsType);
    }

    @Override
    public NewsTypeDto update(Integer id, NewsTypeRequestDto requestDto) {
        NewsType newsType = newsTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NewsType.ERR_NOT_FOUND_ID, new String[]{id.toString()}));

        newsTypeMapper.updateNewsType(newsType, requestDto);
        newsTypeRepository.save(newsType);
        return newsTypeMapper.mapNewsTypeToNewsTypeDto(newsType);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        NewsType newsType = newsTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NewsType.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        newsTypeRepository.delete(newsType);
        return new CommonResponseDto(true, MessageConstant.DELETE_NEWS_TYPE_SUCCESSFULLY);
    }
}
