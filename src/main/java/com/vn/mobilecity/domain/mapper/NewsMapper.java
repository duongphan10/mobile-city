package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.NewsRequestDto;
import com.vn.mobilecity.domain.dto.response.NewsDto;
import com.vn.mobilecity.domain.entity.News;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    News mapNewsCreateDtoToNews(NewsRequestDto createDto);

    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    void updateNews(@MappingTarget News news, NewsRequestDto updateDto);

    @Mappings({
            @Mapping(target = "newsTypeId", source = "newsType.id"),
            @Mapping(target = "newsTypeName", source = "newsType.name"),
    })
    NewsDto mapNewsToNewsDto(News news);

    List<NewsDto> mapNewsToNewsDto(List<News> news);

}
