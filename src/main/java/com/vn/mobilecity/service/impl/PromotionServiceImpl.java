package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.PromotionRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.PromotionDto;
import com.vn.mobilecity.domain.entity.Promotion;
import com.vn.mobilecity.domain.mapper.PromotionMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.PromotionRepository;
import com.vn.mobilecity.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public PromotionDto getById(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return promotionMapper.mapPromotionToPromotionDto(promotion);
    }


    @Override
    public List<PromotionDto> getAll() {
        List<Promotion> promotions = promotionRepository.getAll();
        return promotionMapper.mapPromotionsToPromotionDtos(promotions);
    }

    @Override
    public PromotionDto create(PromotionRequestDto requestDto) {
        Promotion promotion = promotionMapper.mapPromotionRequestDtoToPromotion(requestDto);
        promotionRepository.save(promotion);
        return promotionMapper.mapPromotionToPromotionDto(promotion);
    }

    @Override
    public PromotionDto update(Integer id, PromotionRequestDto requestDto) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{id.toString()}));

        promotionMapper.updatePromotion(promotion, requestDto);
        promotionRepository.save(promotion);
        return promotionMapper.mapPromotionToPromotionDto(promotion);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        promotionRepository.delete(promotion);
        return new CommonResponseDto(true, MessageConstant.DELETE_PROMOTION_SUCCESSFULLY);
    }
}
