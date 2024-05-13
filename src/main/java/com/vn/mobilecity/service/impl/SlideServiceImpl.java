package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.SlideRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.SlideDto;
import com.vn.mobilecity.domain.entity.Slide;
import com.vn.mobilecity.domain.mapper.SlideMapper;
import com.vn.mobilecity.exception.AlreadyExistException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.ProductRepository;
import com.vn.mobilecity.repository.SlideRepository;
import com.vn.mobilecity.service.SlideService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;
    private final ProductRepository productRepository;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public SlideDto getById(Integer id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Slide.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return slideMapper.mapSlideToSlideDto(slide);
    }

    @Override
    public List<SlideDto> getAll(Boolean status) {
        List<Slide> slides = slideRepository.getAll(status);
        return slideMapper.mapSlidesToSlideDtos(slides);
    }

    @Override
    public List<SlideDto> getSlideByUser() {
        List<Slide> slides = slideRepository.getSlideByUser();
        return slideMapper.mapSlidesToSlideDtos(slides);
    }

    @Override
    public SlideDto create(SlideRequestDto createDto) {
        if (slideRepository.existsByPosition(createDto.getPosition())) {
            throw new AlreadyExistException(ErrorMessage.Slide.ERR_POSITION_ALREADY_EXIST, new String[]{createDto.getPosition().toString()});
        }
        Slide slide = slideMapper.mapSlideRequestDtoToSlide(createDto);
        slide.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        return slideMapper.mapSlideToSlideDto(slideRepository.save(slide));
    }

    @Override
    public SlideDto updateById(Integer id, SlideRequestDto updateDto) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Slide.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!Objects.equals(updateDto.getPosition(), slide.getPosition()) && slideRepository.existsByPosition(updateDto.getPosition())) {
            throw new AlreadyExistException(ErrorMessage.Slide.ERR_POSITION_ALREADY_EXIST, new String[]{updateDto.getPosition().toString()});
        }

        slideMapper.updateSlide(slide, updateDto);
        if (updateDto.getAvatar() != null) {
            uploadFileUtil.destroyImageWithUrl(slide.getAvatar());
            slide.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
        }
        return slideMapper.mapSlideToSlideDto(slideRepository.save(slide));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Slide.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        uploadFileUtil.destroyImageWithUrl(slide.getAvatar());
        slideRepository.delete(slide);
        return new CommonResponseDto(true, MessageConstant.DELETE_SLIDE_SUCCESSFULLY);
    }
}
