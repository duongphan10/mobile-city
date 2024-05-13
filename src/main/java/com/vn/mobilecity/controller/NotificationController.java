package com.vn.mobilecity.controller;

import com.vn.mobilecity.base.BaseResponse;
import com.vn.mobilecity.base.RestApiV1;
import com.vn.mobilecity.constant.UrlConstant;
import com.vn.mobilecity.domain.dto.request.NotificationRequestDto;
import com.vn.mobilecity.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class NotificationController {
    private final NotificationService notificationService;

    @Tag(name = "notification-controller")
    @Operation(summary = "API get notification by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Notification.GET_BY_ID)
    public ResponseEntity<?> getNotificationById(@PathVariable Integer id) {
        return BaseResponse.success(notificationService.getById(id));
    }

    @Tag(name = "notification-controller")
    @Operation(summary = "API get all notification")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Notification.GET_ALL)
    public ResponseEntity<?> getAllNotification() {
        return BaseResponse.success(notificationService.getAll());
    }

    @Tag(name = "notification-controller")
    @Operation(summary = "API create notification")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Notification.CREATE)
    public ResponseEntity<?> createNotification(@Valid @RequestBody NotificationRequestDto requestDto) {
        return BaseResponse.success(notificationService.create(requestDto));
    }

    @Tag(name = "notification-controller")
    @Operation(summary = "API update notification")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Notification.UPDATE_BY_ID)
    public ResponseEntity<?> updateNotification(@PathVariable Integer id,
                                                @Valid @RequestBody NotificationRequestDto requestDto) {
        return BaseResponse.success(notificationService.updateById(id, requestDto));
    }

    @Tag(name = "notification-controller")
    @Operation(summary = "API delete notification")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Notification.DELETE_BY_ID)
    public ResponseEntity<?> deleteNotification(@PathVariable Integer id) {
        return BaseResponse.success(notificationService.deleteById(id));
    }
}
