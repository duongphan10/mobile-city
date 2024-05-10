package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.SortByDataConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.pagination.PagingMeta;
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
import com.vn.mobilecity.util.PaginationUtil;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public PaginationResponseDto<SlideDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.SLIDE);
        Page<Slide> slidePage = slideRepository.getAll(pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.SLIDE, slidePage);

        List<SlideDto> slideDtoList = slideMapper.mapSlideToSlideDto(slidePage.getContent());
        return new PaginationResponseDto<>(meta, slideDtoList);
    }

    @Override
    public PaginationResponseDto<SlideDto> getByStatus(PaginationFullRequestDto paginationFullRequestDto, Boolean status) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.SLIDE);
        Page<Slide> slidePage = slideRepository.getByStatus(status, pageable);

        PagingMeta meta = PaginationUtil.buildPagingMeta(paginationFullRequestDto, SortByDataConstant.SLIDE, slidePage);
        List<SlideDto> slideDtoList = slideMapper.mapSlideToSlideDto(slidePage.getContent());
        return new PaginationResponseDto<>(meta, slideDtoList);
    }

    @Override
    public SlideDto create(SlideRequestDto createDto) {
        if (slideRepository.findByPosition(createDto.getPosition()) != null) {
            throw new AlreadyExistException(ErrorMessage.Slide.ERR_POSITION_ALREADY_EXIST, new String[]{createDto.getPosition().toString()});
        }
        Slide slide = slideMapper.mapSlideRequestDtoToSlide(createDto);
        slide.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        return slideMapper.mapSlideToSlideDto(slideRepository.save(slide));
    }

    @Override
    public SlideDto update(Integer id, SlideRequestDto updateDto) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Slide.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        slideMapper.updateSlide(slide, updateDto);

        MultipartFile multipartFile = updateDto.getAvatar();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            uploadFileUtil.destroyImageWithUrl(slide.getAvatar());
            slide.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
        }
        return slideMapper.mapSlideToSlideDto(slideRepository.save(slide));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Slide.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        slideRepository.delete(slide);
        return new CommonResponseDto(true, MessageConstant.DELETE_SLIDE_SUCCESSFULLY);
    }
}
