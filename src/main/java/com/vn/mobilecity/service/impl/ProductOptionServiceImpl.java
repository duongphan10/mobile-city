package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.ProductOptionCreateDto;
import com.vn.mobilecity.domain.dto.request.ProductOptionUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.ProductOptionDto;
import com.vn.mobilecity.domain.entity.Product;
import com.vn.mobilecity.domain.entity.ProductOption;
import com.vn.mobilecity.domain.entity.Promotion;
import com.vn.mobilecity.domain.mapper.ProductOptionMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.ProductOptionRepository;
import com.vn.mobilecity.repository.ProductRepository;
import com.vn.mobilecity.repository.PromotionRepository;
import com.vn.mobilecity.service.ProductOptionService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final ProductOptionMapper productOptionMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ProductOptionDto getById(Integer id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return productOptionMapper.mapProductOptionToProductOptionDto(productOption);
    }

    @Override
    public List<ProductOptionDto> getAllByProductId(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{productId.toString()}));

        List<ProductOption> productOptions = productOptionRepository.getAllByProductId(productId);
        return productOptionMapper.mapProductOptionsToProductOptionDtos(productOptions);
    }

    @Override
    public ProductOptionDto create(ProductOptionCreateDto productOptionCreateDto) {
        Product product = productRepository.findById(productOptionCreateDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{productOptionCreateDto.getProductId().toString()}));
        Promotion promotion = promotionRepository.findById(productOptionCreateDto.getPromotionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{productOptionCreateDto.getPromotionId().toString()}));

        ProductOption productOption = productOptionMapper.mapProductOptionCreateDtoToProductOption(productOptionCreateDto);
        productOption.setImage(uploadFileUtil.uploadImage(productOptionCreateDto.getImage()));
        productOption.setProduct(product);
        productOption.setPromotion(promotion);
        productOptionRepository.save(productOption);
        return productOptionMapper.mapProductOptionToProductOptionDto(productOption);
    }

    @Override
    public ProductOptionDto updateById(Integer id, ProductOptionUpdateDto productOptionUpdateDto) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        Promotion promotion = promotionRepository.findById(productOptionUpdateDto.getPromotionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Promotion.ERR_NOT_FOUND_ID, new String[]{productOptionUpdateDto.getPromotionId().toString()}));

        productOptionMapper.update(productOption, productOptionUpdateDto);
        if (productOptionUpdateDto.getImage() != null) {
            uploadFileUtil.destroyImageWithUrl(productOption.getImage());
            productOption.setImage(uploadFileUtil.uploadImage(productOptionUpdateDto.getImage()));
        }
        productOption.setPromotion(promotion);
        productOptionRepository.save(productOption);
        return productOptionMapper.mapProductOptionToProductOptionDto(productOption);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        productOptionRepository.delete(productOption);
        return new CommonResponseDto(true, MessageConstant.DELETE_PRODUCT_OPTION_SUCCESSFULLY);
    }

}
