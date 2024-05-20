package com.vn.mobilecity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {
    @Value("${notification.order.id}")
    public int ORDER;
    @Value("${notification.status.id}")
    public int STATUS;
    @Value("${notification.review.id}")
    public int REVIEW;
}
