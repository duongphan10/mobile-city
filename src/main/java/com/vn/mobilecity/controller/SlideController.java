package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.SlideRequestDto;
import com.vn.mobilecity.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class SlideController {
    private final SlideService slideService;

    @Tag(name = "slide-controller")
    @Operation(summary = "API get slide by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Slide.GET_BY_ID)
    public ResponseEntity<?> getSlideById(@PathVariable Integer id) {
        return BaseResponse.success(slideService.getById(id));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API get slide by status")
    @GetMapping(UrlConstant.Slide.GET_BY_USER)
    public ResponseEntity<?> getSlideByUser() {
        return BaseResponse.success(slideService.getSlideByUser());
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API get all slide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Slide.GET_ALL)
    public ResponseEntity<?> getAllSlide(@RequestParam(name = "status", required = false) Boolean status) {
        return BaseResponse.success(slideService.getAll(status));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API create slide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Slide.CREATE)
    public ResponseEntity<?> createSlide(@Valid @ModelAttribute SlideRequestDto createDto) {
        return BaseResponse.success(slideService.create(createDto));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API update slide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Slide.UPDATE)
    public ResponseEntity<?> updateSlide(@PathVariable Integer id, @Valid @ModelAttribute SlideRequestDto updateDto) {
        return BaseResponse.success(slideService.updateById(id, updateDto));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API delete slide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Slide.DELETE)
    public ResponseEntity<?> deleteSlide(@PathVariable Integer id) {
        return BaseResponse.success(slideService.deleteById(id));
    }
}
