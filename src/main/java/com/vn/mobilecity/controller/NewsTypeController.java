package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.NewsTypeRequestDto;
import com.vn.mobilecity.service.NewsTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class NewsTypeController {
    private final NewsTypeService newsTypeService;

    @Tag(name = "news-type-controller")
    @Operation(summary = "API get news type by id")
    @GetMapping(UrlConstant.NewsType.GET_BY_ID)
    public ResponseEntity<?> getNewsTypeById(@PathVariable Integer id) {
        return BaseResponse.success(newsTypeService.getById(id));
    }

    @Tag(name = "news-type-controller")
    @Operation(summary = "API get all news type")
    @GetMapping(UrlConstant.NewsType.GET_ALL)
    public ResponseEntity<?> getAllNewsType() {
        return BaseResponse.success(newsTypeService.getAll());
    }

    @Tag(name = "news-type-controller")
    @Operation(summary = "API create news type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.NewsType.CREATE)
    public ResponseEntity<?> createNewsType(@Valid @RequestBody NewsTypeRequestDto requestDto) {
        return BaseResponse.success(newsTypeService.create(requestDto));
    }

    @Tag(name = "news-type-controller")
    @Operation(summary = "API update news type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.NewsType.UPDATE)
    public ResponseEntity<?> updateNewsType(@PathVariable Integer id, @Valid @RequestBody NewsTypeRequestDto requestDto) {
        return BaseResponse.success(newsTypeService.update(id, requestDto));
    }

    @Tag(name = "news-type-controller")
    @Operation(summary = "API delete news type")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.NewsType.DELETE)
    public ResponseEntity<?> deleteNewsType(@PathVariable Integer id) {
        return BaseResponse.success(newsTypeService.deleteById(id));
    }
}
