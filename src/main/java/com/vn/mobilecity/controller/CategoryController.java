package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.CategoryRequestDto;
import com.vn.mobilecity.domain.mapper.CategoryMapper;
import com.vn.mobilecity.repository.CategoryRepository;
import com.vn.mobilecity.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Tag(name = "category-controller")
    @Operation(summary = "API get all category")
    @GetMapping(UrlConstant.Category.GET_ALL)
    public ResponseEntity<?> getAllCategory() {
        return BaseResponse.success(categoryService.getAll());
    }

    @Tag(name = "category-controller")
    @Operation(summary = "API get category by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Category.GET_BY_ID)
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        return BaseResponse.success(categoryService.getById(id));
    }

    @Tag(name = "category-controller")
    @Operation(summary = "API create category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Category.CREATE)
    public ResponseEntity<?> createCategory(@Valid @ModelAttribute CategoryRequestDto createDto) {
        return BaseResponse.success(categoryService.create(createDto));
    }

    @Tag(name = "category-controller")
    @Operation(summary = "API update category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Category.UPDATE)
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @ModelAttribute CategoryRequestDto updateDto) {
        return BaseResponse.success(categoryService.update(id, updateDto));
    }

    @Tag(name = "category-controller")
    @Operation(summary = "API delete category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Category.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return BaseResponse.success(categoryService.deleteById(id));
    }
}
