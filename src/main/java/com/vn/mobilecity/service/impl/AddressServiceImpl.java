package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.domain.dto.request.AddressRequestDto;
import com.vn.mobilecity.domain.dto.response.AddressDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.entity.Address;
import com.vn.mobilecity.domain.entity.User;
import com.vn.mobilecity.domain.mapper.AddressMapper;
import com.vn.mobilecity.exception.ForbiddenException;
import com.vn.mobilecity.exception.InternalServerException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.AddressRepository;
import com.vn.mobilecity.service.AddressService;
import com.vn.mobilecity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserService userService;

    @Override
    public AddressDto getById(Integer id, Integer userId) {
        User user = userService.getById(userId);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!address.getCreatedBy().equals(userId) && !user.getRole().getName().equals(RoleConstant.ADMIN)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public List<AddressDto> getAllByUserId(Integer userId) {
        userService.getById(userId);
        List<Address> addresses = addressRepository.getAllByUserId(userId);
        return addressMapper.mapAddressesToAddressDtos(addresses);
    }

    @Override
    public AddressDto getDefault(Integer userId) {
        Address address = addressRepository.getDefaultByUserId(userId);
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto create(AddressRequestDto addressRequestDto, Integer userId) {
        User user = userService.getById(userId);
        Address address = addressMapper.mapAddressRequestDtoToAddress(addressRequestDto);
        address.setUser(user);
        addressRepository.save(address);
        if (address.getAddressDefault()) {
            addressRepository.removeOtherDefault(address.getId(), userId);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto updateById(Integer id, AddressRequestDto addressRequestDto, Integer userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        if (address.getAddressDefault() && !addressRequestDto.getAddressDefault()) {
            throw new InternalServerException(ErrorMessage.Address.ERR_CANCEL_DEFAULT);
        }
        addressMapper.updateAddress(address, addressRequestDto);
        addressRepository.save(address);
        if (address.getAddressDefault()) {
            addressRepository.removeOtherDefault(address.getId(), userId);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto changeDefaultById(Integer id, Integer userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        addressRepository.removeOtherDefault(address.getId(), userId);
        address.setAddressDefault(true);
        return addressMapper.mapAddressToAddressDto(addressRepository.save(address));
    }

    @Override
    public CommonResponseDto deleteById(Integer id, Integer userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        addressRepository.delete(address);
        return new CommonResponseDto(true, MessageConstant.DELETE_ADDRESS_SUCCESSFULLY);
    }
}
