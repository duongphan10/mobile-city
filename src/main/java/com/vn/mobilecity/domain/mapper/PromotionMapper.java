package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.PromotionRequestDto;
import com.vn.mobilecity.domain.dto.response.PromotionDto;
import com.vn.mobilecity.domain.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {

    PromotionDto mapPromotionToPromotionDto(Promotion promotion);

    List<PromotionDto> mapPromotionsToPromotionDtos(List<Promotion> promotions);

    Promotion mapPromotionRequestDtoToPromotion(PromotionRequestDto requestDto);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionRequestDto requestDto);

}
