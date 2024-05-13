package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.NotificationRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NotificationDto;
import com.vn.mobilecity.domain.entity.Notification;
import com.vn.mobilecity.domain.mapper.NotificationMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.NotificationRepository;
import com.vn.mobilecity.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDto getById(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return notificationMapper.mapNotificationToNotificationDto(notification);
    }

    @Override
    public List<NotificationDto> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.mapNotificationsToNotificationDtos(notifications);
    }

    @Override
    public NotificationDto create(NotificationRequestDto createDto) {
        Notification notification = notificationMapper.mapNotificationRequestDtoToNotification(createDto);
        notificationRepository.save(notification);
        return notificationMapper.mapNotificationToNotificationDto(notification);
    }

    @Override
    public NotificationDto updateById(Integer id, NotificationRequestDto updateDto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{id.toString()}));

        notificationMapper.updateNotification(notification, updateDto);
        notificationRepository.save(notification);
        return notificationMapper.mapNotificationToNotificationDto(notification);
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Notification.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        notificationRepository.delete(notification);
        return new CommonResponseDto(true, MessageConstant.DELETE_NOTIFICATION_SUCCESSFULLY);
    }
}
