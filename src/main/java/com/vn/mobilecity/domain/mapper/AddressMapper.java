package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.AddressRequestDto;
import com.vn.mobilecity.domain.dto.response.AddressDto;
import com.vn.mobilecity.domain.entity.Address;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id")
    })
    AddressDto mapAddressToAddressDto(Address address);

    List<AddressDto> mapAddressesToAddressDtos(List<Address> addresses);

    Address mapAddressRequestDtoToAddress(AddressRequestDto addressRequestDto);

    void updateAddress(@MappingTarget Address address, AddressRequestDto addressRequestDto);

}
