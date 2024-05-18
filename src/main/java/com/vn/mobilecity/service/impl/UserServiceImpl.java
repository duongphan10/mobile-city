package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.constant.RoleConstant;
import com.vn.mobilecity.domain.dto.request.ChangePasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.NewPasswordRequestDto;
import com.vn.mobilecity.domain.dto.request.UserCreateDto;
import com.vn.mobilecity.domain.dto.request.UserUpdateDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.UserDto;
import com.vn.mobilecity.domain.entity.User;
import com.vn.mobilecity.domain.mapper.UserMapper;
import com.vn.mobilecity.exception.AlreadyExistException;
import com.vn.mobilecity.exception.InvalidException;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.RoleRepository;
import com.vn.mobilecity.repository.UserRepository;
import com.vn.mobilecity.service.UserService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UploadFileUtil uploadFileUtil;

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.mapUsersToUserDtos(users);
    }

    @Override
    public UserDto getCurrentUser(Integer id) {
        return this.getUserById(id);
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (userRepository.existsByPhone(userCreateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userCreateDto.getPhone()});
        }
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_EMAIL, new String[]{userCreateDto.getEmail()});
        }
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userCreateDto.getUsername()});
        }
        User user = userMapper.mapUserCreateDtoToUser(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(roleRepository.findByName(RoleConstant.USER.getName()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        userRepository.save(user);
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Integer id, UserUpdateDto userUpdateDto) {
        User user = this.getById(id);
        if (!user.getPhone().equals(userUpdateDto.getPhone()) && userRepository.existsByPhone(userUpdateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userUpdateDto.getPhone()});
        }
        if (!user.getEmail().equals(userUpdateDto.getEmail()) && userRepository.existsByEmail(userUpdateDto.getEmail())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_EMAIL, new String[]{userUpdateDto.getEmail()});
        }
        if (!user.getUsername().equals(userUpdateDto.getUsername()) && userRepository.existsByUsername(userUpdateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userUpdateDto.getUsername()});
        }

        userMapper.updateUser(user, userUpdateDto);
        if (userUpdateDto.getAvatar() != null) {
            if (user.getAvatar() != null) {
                uploadFileUtil.destroyImageWithUrl(user.getAvatar());
            }
            user.setAvatar(uploadFileUtil.uploadImage(userUpdateDto.getAvatar()));
        }
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public CommonResponseDto changePassword(Integer id, ChangePasswordRequestDto changePasswordRequestDto) {
        User user = this.getById(id);
        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())) {
            throw new InvalidException(ErrorMessage.User.ERR_CURRENT_PASSWORD_INCORRECT);
        }

        if (changePasswordRequestDto.getCurrentPassword().equals(changePasswordRequestDto.getNewPassword())) {
            throw new InvalidException(ErrorMessage.User.ERR_SAME_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        userRepository.save(user);
        return new CommonResponseDto(true, MessageConstant.CHANGE_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public CommonResponseDto createNewPassword(Integer id, NewPasswordRequestDto newPasswordRequestDto) {
        User user = this.getById(id);
        user.setPassword(passwordEncoder.encode(newPasswordRequestDto.getPassword()));
        userRepository.save(user);
        return new CommonResponseDto(true, MessageConstant.CREATE_NEW_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        User user = this.getById(id);
        if (user.getAvatar() != null) {
            uploadFileUtil.destroyImageWithUrl(user.getAvatar());
        }
        userRepository.delete(user);
        return new CommonResponseDto(true, MessageConstant.DELETE_USER_SUCCESSFULLY);
    }

}
