package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.NewsTypeRequestDto;
import com.vn.mobilecity.domain.dto.response.NewsTypeDto;
import com.vn.mobilecity.domain.entity.NewsType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsTypeMapper {

    NewsTypeDto mapNewsTypeToNewsTypeDto(NewsType newsType);

    List<NewsTypeDto> mapNewsTypesToNewsTypeDtos(List<NewsType> newsTypes);

    NewsType mapNewsTypeRequestDtoToNewsType(NewsTypeRequestDto requestDto);

    void updateNewsType(@MappingTarget NewsType newsType, NewsTypeRequestDto requestDto);

}
