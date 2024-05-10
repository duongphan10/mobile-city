package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.SortByDataConstant;
import com.vn.mobilecity.domain.dto.pagination.PaginationFullRequestDto;
import com.vn.mobilecity.domain.dto.pagination.PaginationResponseDto;
import com.vn.mobilecity.domain.dto.pagination.PagingMeta;
import com.vn.mobilecity.domain.dto.request.CartCreateDto;
import com.vn.mobilecity.domain.dto.request.CartUpdateDto;
import com.vn.mobilecity.domain.dto.response.CartDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.entity.Cart;
import com.vn.mobilecity.domain.entity.ProductOption;
import com.vn.mobilecity.domain.entity.User;
import com.vn.mobilecity.domain.mapper.CartMapper;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.CartRepository;
import com.vn.mobilecity.repository.ProductOptionRepository;
import com.vn.mobilecity.repository.UserRepository;
import com.vn.mobilecity.service.CartService;
import com.vn.mobilecity.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CartMapper cartMapper;

    @Override
    public PaginationResponseDto<CartDto> getAll(Integer userId, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.CART);
        Page<Cart> cartPage = cartRepository.getAllByUser(userId, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.CART, cartPage);

        List<CartDto> cartDtos = cartMapper.mapCartsToCartDtos(cartPage.getContent());

        return new PaginationResponseDto<>(meta, cartDtos);
    }

    @Override
    public int getNumberItem(Integer userId) {
        return cartRepository.getNumberItem(userId);
    }

    @Override
    public CartDto create(Integer userId, CartCreateDto cartCreateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId.toString()}));
        ProductOption productOption = productOptionRepository.findById(cartCreateDto.getProductOptionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{cartCreateDto.getProductOptionId().toString()}));

        if (productOption.getQuantity() == 0) {
            throw new NotFoundException(ErrorMessage.ProductOption.ERR_OUT_OF_STOCK);
        }
        if (cartCreateDto.getQuantity() > productOption.getQuantity()) {
            throw new NotFoundException(ErrorMessage.Cart.ERR_QUANTITY_EXCEEDED);
        }

        Cart cart = cartRepository.findByUserIdAndProductOptionId(userId, cartCreateDto.getProductOptionId());
        if (cart != null) {
            int newQuantity = cart.getQuantity() + cartCreateDto.getQuantity();
            cart.setQuantity(newQuantity > productOption.getQuantity() ? productOption.getQuantity() : newQuantity);
        } else {
            cart = cartMapper.mapCartCreateDtoToCart(cartCreateDto);
            cart.setUser(user);
            cart.setProductOption(productOption);
        }
        return cartMapper.mapCartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto updateById(Integer userId, Integer id, CartUpdateDto cartUpdateDto) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!cart.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }

        if (cartUpdateDto.getQuantity() > cart.getProductOption().getQuantity()) {
            throw new NotFoundException(ErrorMessage.Cart.ERR_QUANTITY_EXCEEDED);
        }
        cart.setQuantity(cartUpdateDto.getQuantity());

        return cartMapper.mapCartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CommonResponseDto deleteById(Integer userId, Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!cart.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        cartRepository.delete(cart);
        return new CommonResponseDto(true, MessageConstant.DELETE_CART_ITEM_SUCCESSFULLY);
    }
}
