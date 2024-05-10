package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.domain.entity.Review;
import com.vn.mobilecity.domain.entity.ReviewFile;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.ReviewFileRepository;
import com.vn.mobilecity.repository.ReviewRepository;
import com.vn.mobilecity.service.ReviewFileService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewFileServiceImpl implements ReviewFileService {

    private final ReviewFileRepository reviewFileRepository;
    private final ReviewRepository reviewRepository;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public List<ReviewFile> create(Integer reviewId, List<MultipartFile> files) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Review.ERR_NOT_FOUND_ID, new String[]{reviewId.toString()}));
        List<ReviewFile> reviewFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            ReviewFile reviewFile = new ReviewFile();
            reviewFile.setPath(uploadFileUtil.uploadFile(file));
            reviewFile.setName(file.getOriginalFilename());
            reviewFile.setSize(file.getSize());
            reviewFile.setReview(review);
            reviewFileRepository.save(reviewFile);
            reviewFiles.add(reviewFile);
        }
        return reviewFiles;
    }
}
