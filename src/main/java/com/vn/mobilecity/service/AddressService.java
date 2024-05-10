package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.AddressRequestDto;
import com.vn.mobilecity.domain.dto.response.AddressDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface AddressService {
    AddressDto getById(Integer id, Integer userId);

    List<AddressDto> getAllByUserId(Integer userId);

    AddressDto getDefault(Integer userId);

    AddressDto create(AddressRequestDto addressRequestDto, Integer userId);

    AddressDto updateById(Integer id, AddressRequestDto addressRequestDto, Integer userId);

    AddressDto changeDefaultById(Integer id, Integer userId);

    CommonResponseDto deleteById(Integer id, Integer userId);
}
