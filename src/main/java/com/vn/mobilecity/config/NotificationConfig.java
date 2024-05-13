package com.vn.mobilecity.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
    @Value("${notification.order.id}")
    public int ORDER;
    @Value("${notification.review.id}")
    public int REVIEW;
}
