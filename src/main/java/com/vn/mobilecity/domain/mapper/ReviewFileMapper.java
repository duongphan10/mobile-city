package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.response.ReviewFileDto;
import com.vn.mobilecity.domain.entity.ReviewFile;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewFileMapper {

    ReviewFileDto mapReviewFileToReviewFileDto(ReviewFile reviewFile);

    List<ReviewFileDto> mapReviewFilesToReviewFileDtos(List<ReviewFile> reviews);

}
