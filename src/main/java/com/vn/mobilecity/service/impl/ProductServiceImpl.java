package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.CommonConstant;
import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.SortByDataConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.pagination.PagingMeta;
import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductDto;
import com.vn.mobilecity.domain.dto.response.ProductSimpleDto;
import com.vn.mobilecity.domain.entity.Category;
import com.vn.mobilecity.domain.entity.Product;
import com.vn.mobilecity.domain.mapper.ProductMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.CategoryRepository;
import com.vn.mobilecity.repository.ProductRepository;
import com.vn.mobilecity.service.ProductService;
import com.vn.mobilecity.util.PaginationUtil;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public PaginationResponseDto<ProductSimpleDto> getAll(Integer categoryId, PaginationFullRequestDto paginationFullRequestDto) {
        if (categoryId != null) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{categoryId.toString()}));
        }
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_PRODUCT_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);
        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.PRODUCT);
        Page<Product> productPage;
        if (categoryId == null) {
            productPage = productRepository.getAll(pageable);
        } else {
            productPage = productRepository.getAll(categoryId, pageable);
        }

        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.PRODUCT, productPage);

        List<ProductSimpleDto> productSimpleDtos = productMapper.mapProductsToProductSimpleDtos(productPage.getContent());

        return new PaginationResponseDto<>(meta, productSimpleDtos);
    }

    @Override
    public PaginationResponseDto<ProductSimpleDto> search(PaginationFullRequestDto paginationFullRequestDto) {
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_PRODUCT_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);

        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.PRODUCT);
        Page<Product> productPage = productRepository.search(paginationFullRequestDto.getKeyword(), pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.PRODUCT, productPage);

        List<ProductSimpleDto> productSimpleDtos = productMapper.mapProductsToProductSimpleDtos(productPage.getContent());

        return new PaginationResponseDto<>(meta, productSimpleDtos);
    }

    @Override
    public ProductDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId().toString()}));

        Product product = productMapper.mapProductRequestDtoToProduct(productRequestDto);
        product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        product.setCategory(category);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateById(Integer id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId().toString()}));
        productMapper.update(product, productRequestDto);
        uploadFileUtil.destroyImageWithUrl(product.getAvatar());
        product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        product.setCategory(category);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        productRepository.delete(product);
        return new CommonResponseDto(true, MessageConstant.DELETE_PRODUCT_SUCCESSFULLY);
    }
}
