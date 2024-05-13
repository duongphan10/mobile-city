package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.SlideRequestDto;
import com.vn.mobilecity.domain.dto.response.SlideDto;
import com.vn.mobilecity.domain.entity.Slide;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SlideMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    Slide mapSlideRequestDtoToSlide(SlideRequestDto createDto);

    SlideDto mapSlideToSlideDto(Slide slide);

    List<SlideDto> mapSlidesToSlideDtos(List<Slide> slides);

    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    void updateSlide(@MappingTarget Slide slide, SlideRequestDto updateDto);

}
