package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.ChangePasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.NewPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.UserCreateDto;
import com.vn.mobilecity.domain.dto.request.UserUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.UserDto;
import com.vn.mobilecity.domain.entity.User;

import java.util.List;

public interface UserService {
    User getById(Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAll();

    UserDto getCurrentUser(Integer id);

    UserDto create(UserCreateDto userCreateDto);

    UserDto update(Integer id, UserUpdateDto userUpdateDto);

    CommonResponseDto changePassword(Integer id, ChangePasswordRequestDto changePasswordRequestDto);

    CommonResponseDto createNewPassword(Integer id, NewPasswordRequestDto newPasswordRequestDto);

    CommonResponseDto deleteById(Integer id);
}
