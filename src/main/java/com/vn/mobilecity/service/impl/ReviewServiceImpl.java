package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.SortByDataConstant;
import com.vn.mobilecity.constant.StatusConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.pagination.PagingMeta;
import com.vn.mobilecity.domain.dto.request.ReviewCreateDto;
import com.vn.mobilecity.domain.dto.request.ReviewUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ReviewDto;
import com.vn.mobilecity.domain.entity.OrderDetail;
import com.vn.mobilecity.domain.entity.Review;
import com.vn.mobilecity.domain.entity.ReviewFile;
import com.vn.mobilecity.domain.mapper.ReviewMapper;
import com.vn.mobilecity.exception.AlreadyExistException;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.*;
import com.vn.mobilecity.service.ReviewFileService;
import com.vn.mobilecity.service.ReviewService;
import com.vn.mobilecity.util.PaginationUtil;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ReviewFileRepository reviewFileRepository;
    private final ReviewFileService reviewFileService;
    private final ReviewMapper reviewMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ReviewDto getById(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Review.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return reviewMapper.mapReviewToReviewDto(review);
    }

    @Override
    public PaginationResponseDto<ReviewDto> search(Integer productId, Integer star, PaginationFullRequestDto paginationFullRequestDto) {
        productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{productId.toString()}));

        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.REVIEW);
        Page<Review> reviewPage = reviewRepository.search(productId, star, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.REVIEW, reviewPage);
        List<ReviewDto> reviewDtos = reviewMapper.mapReviewsToReviewDtos(reviewPage.getContent());
        return new PaginationResponseDto<>(meta, reviewDtos);
    }

    @Override
    public ReviewDto create(Integer userId, ReviewCreateDto reviewCreateDto) {
        OrderDetail orderDetail = orderDetailRepository.findById(reviewCreateDto.getOrderDetailId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{reviewCreateDto.getOrderDetailId().toString()}));
        if (!orderDetail.getOrder().getOrderStatus().getId().equals(StatusConstant.DELIVERED.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        if (orderDetail.getReview() != null) {
            throw new AlreadyExistException(ErrorMessage.Review.ERR_ALREADY_EXIST_WITH_ORDER_DETAIL);
        }
        if (!orderDetail.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        Review review = reviewMapper.mapReviewCreateDtoToReview(reviewCreateDto);
        review.setOrderDetail(orderDetail);
        reviewRepository.save(review);
        List<ReviewFile> reviewFiles = new ArrayList<>();
        if (reviewCreateDto.getFiles() != null && !reviewCreateDto.getFiles().isEmpty()) {
            reviewFiles = reviewFileService.create(review.getId(), reviewCreateDto.getFiles());
        }
        review.setFiles(reviewFiles);
        return reviewMapper.mapReviewToReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDto updateById(Integer userId, Integer id, ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Review.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!review.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        reviewMapper.update(review, reviewUpdateDto);
        if (review.getFiles() != null && !review.getFiles().isEmpty()) {
            for (ReviewFile reviewFile : review.getFiles()) {
                uploadFileUtil.destroyFileWithUrl(reviewFile.getPath());
            }
            reviewFileRepository.deleteAllByReviewId(review.getId());
        }
        List<ReviewFile> reviewFiles = new ArrayList<>();
        if (reviewUpdateDto.getFiles() != null && !reviewUpdateDto.getFiles().isEmpty()) {
            reviewFiles = reviewFileService.create(review.getId(), reviewUpdateDto.getFiles());
        }
        review.setFiles(reviewFiles);
        return reviewMapper.mapReviewToReviewDto(reviewRepository.save(review));
    }

    @Override
    public CommonResponseDto deleteById(Integer userId, Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Review.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!review.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        review.getOrderDetail().setReview(null);
        reviewRepository.delete(review);
        return new CommonResponseDto(true, MessageConstant.DELETE_REVIEW_SUCCESSFULLY);
    }
}
