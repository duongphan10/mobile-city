package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.NewsRequestDto;
import com.vn.mobilecity.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class NewsController {
    private final NewsService newsService;

    @Tag(name = "news-controller")
    @Operation(summary = "API get news by id")
    @GetMapping(UrlConstant.News.GET_BY_ID)
    public ResponseEntity<?> getNewsById(@PathVariable Integer id) {
        return BaseResponse.success(newsService.getById(id));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API get all news")
    @GetMapping(UrlConstant.News.GET_BY_USER)
    public ResponseEntity<?> getByUser(@RequestParam(required = false, defaultValue = "-1") Integer newsTypeId) {
        return BaseResponse.success(newsService.getByUser(newsTypeId));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API get news by status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.News.GET_ALL)
    public ResponseEntity<?> getAllNews(@RequestParam(required = false, defaultValue = "-1") Integer newsTypeId,
                                        @RequestParam(required = false) Boolean status) {
        return BaseResponse.success(newsService.getAll(newsTypeId, status));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API create news")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.News.CREATE)
    public ResponseEntity<?> createNews(@Valid @ModelAttribute NewsRequestDto createDto) {
        return BaseResponse.success(newsService.create(createDto));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API update news")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.News.UPDATE)
    public ResponseEntity<?> updateNews(@PathVariable Integer id, @Valid @ModelAttribute NewsRequestDto updateDto) {
        return BaseResponse.success(newsService.update(id, updateDto));
    }

    @Tag(name = "news-controller")
    @Operation(summary = "API delete news")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.News.DELETE)
    public ResponseEntity<?> deleteNews(@PathVariable Integer id) {
        return BaseResponse.success(newsService.deleteById(id));
    }
}
