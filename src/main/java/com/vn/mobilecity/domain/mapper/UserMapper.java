package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.constant.CommonConstant;
import com.vn.mobilecity.domain.dto.request.UserCreateDto;
import com.vn.mobilecity.domain.dto.request.UserUpdateDto;
import com.vn.mobilecity.domain.dto.response.UserDto;
import com.vn.mobilecity.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User mapUserCreateDtoToUser(UserCreateDto userCreateDTO);

    @Mappings({
            @Mapping(target = "roleName", source = "role.name")
    })
    UserDto mapUserToUserDto(User user);

    List<UserDto> mapUsersToUserDtos(List<User> user);

    @Mappings({
            @Mapping(target = "birthday", source = "birthday", dateFormat = CommonConstant.PATTERN_DATE),
            @Mapping(target = "avatar", source = "avatar", ignore = true)
    })
    void updateUser(@MappingTarget User user, UserUpdateDto userUpdateDto);
}
