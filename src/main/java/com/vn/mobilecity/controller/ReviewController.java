package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.ReviewCreateDto;
import com.vn.mobilecity.domain.dto.request.ReviewUpdateDto;
import com.vn.mobilecity.security.CurrentUser;
import com.vn.mobilecity.security.UserPrincipal;
import com.vn.mobilecity.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "review-controller")
    @Operation(summary = "API get review by id")
    @GetMapping(UrlConstant.Review.GET_BY_ID)
    public ResponseEntity<?> getReviewById(@PathVariable Integer id) {
        return BaseResponse.success(reviewService.getById(id));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API get all review by product")
    @GetMapping(UrlConstant.Review.GET_ALL)
    public ResponseEntity<?> getReview(@RequestParam(name = "productId") Integer productId,
                                       @RequestParam(name = "star", required = false) Integer star) {
        return BaseResponse.success(reviewService.search(productId, star));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API count star review by product")
    @GetMapping(UrlConstant.Review.GET_STAR)
    public ResponseEntity<?> getStarReview(@RequestParam(name = "productId") Integer productId) {
        return BaseResponse.success(reviewService.countStar(productId));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API create review")
    @PostMapping(UrlConstant.Review.CREATE)
    public ResponseEntity<?> createReview(@Valid @ModelAttribute ReviewCreateDto reviewCreateDto,
                                          @Parameter(name = "principal", hidden = true)
                                          @CurrentUser UserPrincipal user) {
        return BaseResponse.success(reviewService.create(user.getId(), reviewCreateDto));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API update review by id")
    @PatchMapping(UrlConstant.Review.UPDATE)
    public ResponseEntity<?> updateReviewById(@PathVariable Integer id,
                                              @Valid @ModelAttribute ReviewUpdateDto reviewUpdateDto,
                                              @Parameter(name = "principal", hidden = true)
                                              @CurrentUser UserPrincipal user) {
        return BaseResponse.success(reviewService.updateById(user.getId(), id, reviewUpdateDto));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API delete review by id")
    @DeleteMapping(UrlConstant.Review.DELETE)
    public ResponseEntity<?> deleteReviewById(@PathVariable Integer id,
                                              @Parameter(name = "principal", hidden = true)
                                              @CurrentUser UserPrincipal user) {
        return BaseResponse.success(reviewService.deleteById(user.getId(), id));
    }

}
