package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.PromotionConstant;
import com.vn.mobilecity.domain.dto.request.ProductRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductDto;
import com.vn.mobilecity.domain.entity.Category;
import com.vn.mobilecity.domain.entity.Product;
import com.vn.mobilecity.domain.entity.Promotion;
import com.vn.mobilecity.domain.mapper.ProductMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.CategoryRepository;
import com.vn.mobilecity.repository.ProductRepository;
import com.vn.mobilecity.repository.PromotionRepository;
import com.vn.mobilecity.service.ProductService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PromotionRepository promotionRepository;
    private final ProductOptionServiceImpl productOptionService;
    private final ProductMapper productMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll(Integer categoryId, Integer promotionId) {
        if (categoryId != null) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{categoryId.toString()}));
        }
        if (promotionId != null) {
            promotionRepository.findById(promotionId)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{promotionId.toString()}));
        }
        List<Product> products = productRepository.getAll(categoryId, promotionId);
        return productMapper.mapProductsToProductDtos(products);
    }

    @Override
    public List<ProductDto> search(String key, Long priceFrom, Long priceTo) {
        if (priceFrom == null) {
            priceFrom = 0L;
        }
        if (priceTo == null) {
            priceTo = Long.MAX_VALUE;
        }
        List<Product> products = productRepository.search(key, priceFrom, priceTo);
        return productMapper.mapProductsToProductDtos(products);
    }

    @Override
    public ProductDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId().toString()}));
        Promotion promotion = promotionRepository.findById(productRequestDto.getPromotionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getPromotionId().toString()}));

        Product product = productMapper.mapProductRequestDtoToProduct(productRequestDto);
        product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        product.setCategory(category);
        product.setPromotion(promotion);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateById(Integer id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId().toString()}));
        Promotion promotion = promotionRepository.findById(productRequestDto.getPromotionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getPromotionId().toString()}));

        productMapper.update(product, productRequestDto);
        if (productRequestDto.getAvatar() != null) {
            uploadFileUtil.destroyImageWithUrl(product.getAvatar());
            product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        }
        product.setCategory(category);
        product.setPromotion(promotion);
        productRepository.save(product);
        productOptionService.calNewPriceOfProductOption(product, product.getProductOptions());
        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        uploadFileUtil.destroyImageWithUrl(product.getAvatar());
        productRepository.delete(product);
        return new CommonResponseDto(true, MessageConstant.DELETE_PRODUCT_SUCCESSFULLY);
    }
}
