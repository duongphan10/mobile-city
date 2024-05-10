package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.ReviewCreateDto;
import com.vn.mobilecity.domain.dto.request.ReviewUpdateDto;
import com.vn.mobilecity.domain.dto.response.ReviewDto;
import com.vn.mobilecity.domain.entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {
    @Mappings({
            @Mapping(target = "files", ignore = true),
    })
    Review mapReviewCreateDtoToReview(ReviewCreateDto reviewCreateDto);

    @Mappings({
            @Mapping(target = "orderDetailId", source = "orderDetail.id"),
            @Mapping(target = "fileDtos", source = "files"),
    })
    ReviewDto mapReviewToReviewDto(Review review);

    List<ReviewDto> mapReviewsToReviewDtos(List<Review> reviews);

    @Mappings({
            @Mapping(target = "star", source = "star"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "files", ignore = true),
    })
    void update(@MappingTarget Review review, ReviewUpdateDto reviewUpdateDto);

}
