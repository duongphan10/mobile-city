package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.entity.ReviewFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewFileService {

    List<ReviewFile> create(Integer reviewId, List<MultipartFile> files);

}
