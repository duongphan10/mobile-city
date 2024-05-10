package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.CategoryRequestDto;
import com.vn.mobilecity.domain.dto.response.CategoryDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.entity.Category;
import com.vn.mobilecity.domain.mapper.CategoryMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.CategoryRepository;
import com.vn.mobilecity.service.CategoryService;
import com.vn.mobilecity.service.UserService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UploadFileUtil uploadFileUtil;
    private final UserService userService;

    @Override
    public CategoryDto getById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto create(CategoryRequestDto createDto) {
        Category category = categoryMapper.mapCategoryCreateDtoToCategory(createDto);
        category.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Integer id, CategoryRequestDto updateDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id.toString()}));

        categoryMapper.updateCategory(category, updateDto);
        uploadFileUtil.destroyImageWithUrl(category.getAvatar());
        category.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        categoryRepository.delete(category);
        return new CommonResponseDto(true, MessageConstant.DELETE_CATEGORY_SUCCESSFULLY);
    }
}
