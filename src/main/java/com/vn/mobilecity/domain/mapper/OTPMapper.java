package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.response.OTPDto;
import com.vn.mobilecity.domain.entity.OTP;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OTPMapper {
    @Mappings({
            @Mapping(target = "expirationTime", source = "expirationTime"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "userId", source = "user.id"),
    })
    OTPDto otpToOTPDto(OTP otp);

}
