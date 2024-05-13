package com.vn.mobilecity.domain.mapper;

import com.vn.mobilecity.domain.dto.request.NotificationRequestDto;
import com.vn.mobilecity.domain.dto.response.NotificationDto;
import com.vn.mobilecity.domain.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationMapper {

    NotificationDto mapNotificationToNotificationDto(Notification notification);

    List<NotificationDto> mapNotificationsToNotificationDtos(List<Notification> notifications);

    Notification mapNotificationRequestDtoToNotification(NotificationRequestDto notificationRequestDto);

    void updateNotification(@MappingTarget Notification notification, NotificationRequestDto requestDto);

}
