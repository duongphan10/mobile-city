package com.vn.mobilecity.service;

import com.vn.mobilecity.domain.dto.request.NotificationRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto getById(Integer id);

    List<NotificationDto> getAll();

    NotificationDto create(NotificationRequestDto createDto);

    NotificationDto updateById(Integer id, NotificationRequestDto updateDto);

    CommonResponseDto deleteById(Integer id);

}
